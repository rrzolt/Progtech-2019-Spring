package Model;
import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;

public class Statistics{
    public static LoadData data;
    public Statistics(String sourceXml, String jackpotSourceHtml) throws Exception {

        this.data=new LoadData(sourceXml,jackpotSourceHtml);
    }

    public static String weekPrize() {
        return "Eheti vátható főnyeremény\n"+ data.jackPot5;
    }

    /*USER NUMBERS*/
    public static Long numberOfTimesPicked(int number) {
        return data.otoshetek
                .stream()
                .filter(O->Arrays.stream(O.getSzamok()).anyMatch(sz->sz==number))
                .count();
    }


    public static double numberPercentage(int number) {
        return (double) numberOfTimesPicked(number)/(double)data.otoshetek
                .stream()
                .count()*100;
    }


    public static int numberOfBeingInFiveHits(int number) {
        return (int) data.otoshetek
                .stream()
                .filter(O->O.getOtosdb()>0)
                .filter(O->Arrays.stream(O.getSzamok()).anyMatch(sz->sz==number))
                .count();
    }


    public static String lastTimePicked(int number) {
        return String.valueOf(data.otoshetek
                .stream()
                .filter(O-> Arrays.stream(O.getSzamok()).anyMatch(sz->sz==number))
                .map(OtosLottoHet::getDatum)
                .findFirst().get());
    }

    /*FIVE MOST COMMON PICKS*/
    public static List<Integer> fiveMostCommon() {
        Map<Integer,Integer> commons=new TreeMap<>();
        for (int[] numbers:data.otoshetek.stream().map(O->O.getSzamok()).collect(Collectors.toList())) {
            for (int number:numbers) {
                if(commons.containsKey(number))
                    commons.put(number, commons.get(number) + 1);
                else commons.put(number,1);
            }
        }
        return sortMapByValue(commons);
    }

    /*CURRENT AND PREVIOUS PRIZES*/
    public static List<Long> peviousWinningsAndCurrent() {
        List<Long> list = new ArrayList<>();
        int nb=0;
        for (OtosLottoHet O : data.otoshetek) {
            if(nb>9)
                break;
            if (O.getOtosdb() > 0) {
                list.add(0,O.getOtosFt());
                nb++;
            }
        }
        Long max=data.otoshetek
                .stream()
                .filter(O->O.getOtosdb()>0)
                .sorted((a,b)->Long.compare(b.getOtosFt(),a.getOtosFt()))
                .collect(Collectors.toList())
                .get(0)
                .getOtosFt();


        list.add(0,max);
        list.add(list.size(),Long.parseLong(data.jackPot5.split(" ")[0])*1000000);
        return list;
    }

    /*WINNING NUMBERS*/
    public static List<Integer> numbersInFiveHits() {
        Map<Integer,Integer> commons=new TreeMap<>();
        for (int[] numbers:data.otoshetek.stream().filter(O->O.getOtosdb()>0).map(O->O.getSzamok()).collect(Collectors.toList())) {
            for (int number:numbers) {
                if(commons.containsKey(number))
                    commons.put(number, commons.get(number) + 1);
                else commons.put(number,1);
            }
        }
        return sortMapByValue(commons);
    }

    private static List<Integer> sortMapByValue(Map<Integer, Integer> mapToSort) {
        return mapToSort
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue((e1,e2)->e2.intValue()-e1.intValue())).map(e->e.getKey())
                .collect(Collectors.toList()).subList(0,5);
    }
}
