package info.keeper.mockito_test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
/**
 * @ExtendWith(MockitoExtension.class) is used to integrate Mockito with JUnit 5,
 * allowing you to use Mockito's mocking capabilities within your JUnit 5 tests.
 * It initializes mocks annotated with @Mock, verifies interactions,
 * and allows you to use Mockito's verification and stubbing features in your tests.
 * As opposed to loading entire application context loading using @SpringBootTest
 * //@SpringBootTest
 */
@ExtendWith(MockitoExtension.class)
public class MockitoTest {

    static List mockedList;

    @BeforeAll
    @Test
    public static void itShouldMockTheListAndAssertThatItIsNotNull() {
        mockedList = mock(List.class);
        assertNotNull(mockedList);
    }

    @DisplayName("Experiment the mocking and verify the behaviours")
    @Test
    public void itShouldPerformListOperationsAndVerifyTheMockingResult(){
        mockedList.clear();
        mockedList.add("ANY_ITEM");
        mockedList.add(anyString());
        mockedList.add("ANOTHER_ITEM");
        verify(mockedList, times(1)).clear(); // clear was called once
        verify(mockedList, times(3)).add(anyString()); // add was called thrice
        verify(mockedList, times(1)).add("ANY_ITEM"); // but add with argument "ANY_ITEM" was called only ONCE
        /**
         * IT FAILS because NOT_EXISTING_ITEM has not been added at all, even though anyString() has been added but
         * it doesn't work the way it works the other way around.
         */
        // verify(mockedList, times(1)).add("NOT_EXISTING_ITEM"); FAILS
    }

    @DisplayName("Manipulate the list and do the stubbing")
    @Test
    public void itShouldPerformActionOnListAndDoStubbingAndVerify() {
        when(mockedList.get(0)).thenReturn("ANY_ITEM");
        when(mockedList.get(100)).thenThrow(new IndexOutOfBoundsException());
    }

    @Test
    @DisplayName("Argument matchers experiment")
    public void itShouldPerformArgumentMatcherOperationsAndVerify() {
        when(mockedList.get(anyInt())).thenReturn(true);
        when(mockedList.get(200)).thenThrow(new IndexOutOfBoundsException());
        assertTrue((boolean) mockedList.get(100));
        // reset(mock); mock can also be reset, it this time, all stubbing and mocking will be forgotten.
        when(mockedList.add(eq("test"))).thenReturn(true);
        when(mockedList.add("RANDOM")).thenReturn(false);
        when(mockedList.add(anyString())).thenReturn(false);
    }

}
