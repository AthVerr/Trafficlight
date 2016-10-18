package agent;




import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.TestCase;

public class TimeServerTest  {
	TimeServerLinked q = new TimeServerLinked();
	private static final double EPSILON = 0.0;

	

	@Test
	public void ThatEmptySizeIsZero() {
		assertEquals(0, q.size());
	}
	@Test
	public void ThatDequeueOnEmptyThrowsIndexOutOfBoundsException() {
		boolean exceptionOccurred = false;

		try {
			@SuppressWarnings("unused")
			Agent o = q.dequeue();
		} catch (java.util.NoSuchElementException e) {
			exceptionOccurred = true;
		}

		assertTrue(exceptionOccurred);
	}
	@Test
	public void ThatEnqueueFollowedByDequeueReturnsSameReference() {
		class TestThatEnqueueFollowedByDequeueReturnsSameReference implements
				Agent {
			public void run() {
			}
		}

		Agent x1 = new TestThatEnqueueFollowedByDequeueReturnsSameReference();
		q.enqueue(0, x1);
		assertSame(x1, q.dequeue());
		assertEquals(0, q.size());
	}

	public void testThatElementsAreInsertedInOrder() {
		class TestThatElementsAreInsertedInOrder implements Agent {
			public void run() {
			}
		}

		Agent x1 = new TestThatElementsAreInsertedInOrder();
		Agent x2 = new TestThatElementsAreInsertedInOrder();
		q.enqueue(0, x2);
		q.enqueue(1, x1);
		assertSame(x2, q.dequeue());
		assertSame(x1, q.dequeue());
		q.enqueue(1, x1);
		q.enqueue(0, x2);
		assertSame(x2, q.dequeue());
		assertSame(x1, q.dequeue());
		q.enqueue(0, x1);
		q.enqueue(0, x2);
		assertSame(x1, q.dequeue());
		assertSame(x2, q.dequeue());
		q.enqueue(0, x2);
		q.enqueue(0, x1);
		assertSame(x2, q.dequeue());
		assertSame(x1, q.dequeue());
	}

	public void testToString() {
		class TestToString implements Agent {
			public void run() {
			}

			public String toString() {
				return "x";
			}
		}

		q.enqueue(0, new TestToString());
		q.enqueue(1, new TestToString());
		assertEquals("[(0.0,x);(1.0,x)]", q.toString());
	}

	public void testCurrentTime() {
		class TestCurrentTime implements Agent {
			public void run() {
			}
		}

		double expected = 1230;
		q.enqueue(expected, new TestCurrentTime());

		assertEquals(0.0, q.currentTime(),EPSILON);
		q.run(expected);

		assertEquals(expected, q.currentTime(),EPSILON);
	}

	private double _scratch;

	public void testDoActionsAtOrBefore() {
		class TestDoActionsAtOrBefore implements Agent {
			private double _myScratch;

			TestDoActionsAtOrBefore(double myScratch) {
				_myScratch = myScratch;
			}

			public void run() {
				_scratch = _myScratch;
			}
		}

		double time1 = 12;
		double time2 = 23;
		double value1 = 42;
		double value2 = 27;

		q.enqueue(time1, new TestDoActionsAtOrBefore(value1));

		_scratch = 0;
		q.run(time1 - 1);
		assertEquals(0.0, _scratch,EPSILON);

		_scratch = 0;
		q.run(1);
		assertEquals(value1, _scratch,EPSILON);

		q.enqueue(time2, new TestDoActionsAtOrBefore(value2));

		_scratch = 0;
		q.run(time2);
		assertEquals(value2, _scratch,EPSILON);
	}
}