package com.me.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JCThreadLocal {

	private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

	Random r = new Random();

	public static void main(String[] args) throws InterruptedException {

		for (int i = 0; i < 1000; i++) {
			int id = i;
			threadPool.submit(() -> {
				String birthDate = new JCThreadLocal().birthDate(id);
				System.out.println(birthDate);
			});
			
			Thread.sleep(1000);
		}
	}

	public String birthDate(int id) {
		Date birthDate = birthDateFromList(id);
		
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		final DateFormat df = ThreadSafeDateFormatter.df.get();
		return df.format(birthDate);
	}

	public Date birthDateFromList(int id) {
		String[] list = { "2018-03-15 12:54:01", "2021-05-17 12:54:01", "2019-08-15 12:54:01", "2019-03-15 12:54:01",
				"2017-09-15 12:54:01" };
		int index = ((r.nextInt() + id) % list.length);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return formatter.parse(list[index]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}




/**
 * @author ranjit
 * link : https://www.youtube.com/watch?v=sjMe9aecW_A&list=PLhfHPmPYPPRk6yMrcbfafFGSbE2EPK_A6&index=2
 * Added thread local because if we inilitize SimpleDateFormat globally also its not thread safe because multiple thread will access the same 
 * SimpleDateFormat object so using thread local to create one copy of each thread from thread pool.  
 */
class ThreadSafeDateFormatter {

//	public static ThreadLocal<SimpleDateFormat> dateFormatter = new ThreadLocal<SimpleDateFormat>() {
//
//		@Override
//		protected SimpleDateFormat initialValue() {
//			return new SimpleDateFormat("yyyy-MM-dd");
//		}
//
//		@Override
//		public SimpleDateFormat get() {
//			return super.get();
//		}
//
//	};
	
	//same implementation for above code in java8 with factory method withInitial().
	
	static final ThreadLocal<SimpleDateFormat> df = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
	
	
}
