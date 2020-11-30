package my;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MockitoTest {
    private List mockedList;

    @BeforeEach
    public void beforeEach() {
//        mockedList = mock(LinkedList.class);
        mockedList = mock(List.class);
    }

    @Test
    public void testVerify() {
        //using mock object
        mockedList.add("one");
        mockedList.clear();

        //verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    @Test
    public void testStubbing() {
        //stubbing
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        //following prints "first"
        assertEquals("first", mockedList.get(0));

        //following throws runtime exception
        assertThrows(RuntimeException.class, () -> mockedList.get(1));

        //following prints "null" because get(999) was not stubbed
        assertNull(mockedList.get(999));

        //Although it is possible to verify a stubbed invocation, usually it's just redundant
        //If your code cares what get(0) returns, then something else breaks (often even before verify() gets executed).
        //If your code doesn't care what get(0) returns, then it should not be stubbed.
        verify(mockedList).get(0);
    }

    @Test
    public void testStubVoid() {
        doThrow(new RuntimeException()).when(mockedList).clear();

        //following throws RuntimeException:
        assertThrows(RuntimeException.class,
                () -> {
                    mockedList.clear();
                }
        );
    }

    @Test
    public void testSpy() {
        List list = new LinkedList();
        List spy = spy(list);

        //optionally, you can stub out some methods:
        when(spy.size()).thenReturn(100);

        //using the spy calls *real* methods
        spy.add("one");
        spy.add("two");

        //prints "one" - the first element of a list
        assertEquals("one", spy.get(0));

        //size() method was stubbed - 100 is printed
        assertEquals(100, spy.size());

        when(spy.size()).thenCallRealMethod();
        assertEquals(2, spy.size());

        //optionally, you can verify
        verify(spy).add("one");
        verify(spy).add("two");
    }
}
