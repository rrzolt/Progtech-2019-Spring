import Model.Statistics;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsTest {

    private Statistics stat;

    @BeforeEach
    void setUp() throws Exception {
        stat=new Statistics("Test.xml","https://bet.szerencsejatek.hu/jatekok/otoslotto/sorsolasok");
        stat.data.jackPot5="462 millió Ft";
    }

    @AfterEach
    void tearDown() {
        stat=null;
    }

    @Test
    void weekPrize() {
        assertEquals("Eheti vátható főnyeremény\n" + "462 millió Ft"
                ,stat.weekPrize());}

    @Test
    void numberOfTimesPicked() {
        assertEquals(Long.valueOf(2)
                ,stat.numberOfTimesPicked(5));
    }

    @Test
    void numberPercentage() {
        assertEquals(10.526315789473683
                ,stat.numberPercentage(5));
    }

    @Test
    void numberOfBeingInFiveHits() {
        assertEquals(2,stat.numberOfBeingInFiveHits(5));
    }

    @Test
    void lastTimePicked() {
        assertEquals("2017.11.11."
                ,stat.lastTimePicked(5));
    }

    @Test
    void fiveMostCommon() {
        List<Integer> list=new ArrayList<>();
        list.add(1);
        list.add(47);
        list.add(50);
        list.add(55);
        list.add(67);
        assertEquals(list,
                stat.fiveMostCommon());
    }

    @Test
    void peviousWinningsAndCurrent() {
        List<Long> list=new ArrayList<>();
        list.add(4194319530L);
        list.add(2222319530L);
        list.add(1428694065L);
        list.add(687070585L);
        list.add(906255450L);
        list.add(1667298070L);
        list.add(1846186825L);
        list.add(4194319530L);
        list.add(2914319530L);
        list.add(194319530L);
        list.add(219000530L);
        list.add(462000000L);
        assertEquals(list,stat.peviousWinningsAndCurrent());
    }

    @Test
    void numbersInFiveHits() {
        List<Integer> list=new ArrayList<>();
        list.add(1);
        list.add(47);
        list.add(50);
        list.add(5);
        list.add(11);
        assertEquals(list,
                stat.numbersInFiveHits());
    }
}