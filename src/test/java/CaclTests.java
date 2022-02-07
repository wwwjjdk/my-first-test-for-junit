import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class CaclTests {
    Calc sut;

    @AfterEach
    public void afterEachTest(){
        sut = null;
        System.out.println("Test closed for:" + System.currentTimeMillis() + " ms");
    }

    @AfterAll
    public static void afterAllTest(){
        System.out.println("Tests started");
    }

    @BeforeAll
    public static void beforeAllTest(){
        System.out.println("Tests closed ");
    }

    @BeforeEach
    public void beforeEachTest(){
        sut = new Calc();
        System.out.println("Test started");
    }


    @Test
    public void plusTest(){
        //arrange
        int x = 2, y = 3 ;
        int expected = 5;

        //act
        int result = sut.plus.apply(x,y);

        //assert
       assertThat(expected,is(equalTo(result)));// или без из как я понял разницы 0.0
    }

    @Test
    public void throwExceptionTest(){
        int x = 2, y = 0;
        var expected = ArithmeticException.class;
        Assertions.assertThrows(expected,() -> sut.divide.apply(x,y)); // проверка, что исключение выбросится
    }

    @Test
    public void powAndAbsTest(){
        //arrange
        int x = -12, expected = 144, expected2 = 12;
        //act
        int result = sut.pow.apply(x);
        int result2 = sut.abs.apply(x);
        //assert
        Assertions.assertEquals(expected,result);
        Assertions.assertEquals(expected2, result2);

    }

    @ParameterizedTest
    @MethodSource("argumentsStream")
    public void param_plus_pest(int x, int y, int excpected){
        //act
        int result = sut.plus.apply(x,y);
        //assert 
       Assertions.assertEquals(excpected,result); 
    }

   public static Stream<Arguments> argumentsStream(){
        return Stream.of(Arguments.of(30,30,60),Arguments.of(25,25,50));
   }

   @ParameterizedTest
   @MethodSource("simplePlus")
    public void simple_plus_test(int x,int y,String expected){
        //act
       String result = sut.simplePlus.apply(Integer.toString(x), Integer.toString(y));

       //assert
       Assertions.assertEquals(expected,result);
   }

   public static Stream<Arguments> simplePlus(){
        return Stream.of(Arguments.of(1,2,"12"),Arguments.of(13,15,"1315"));
   }

   @ParameterizedTest
   @MethodSource("doesNotThrowException")
    public void last(int x, int y /* int excpected*/){
       //assert
       Assertions.assertDoesNotThrow(()->sut.divide.apply(x,y));
   }

   public static Stream<Arguments> doesNotThrowException(){
        return Stream.of(Arguments.of(1,2),Arguments.of(5,2));
   }



}
