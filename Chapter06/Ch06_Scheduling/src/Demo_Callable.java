import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class Demo_Callable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Observable.fromCallable(new Callable() {

			@Override
			public Object call() throws Exception {
				// TODO Auto-generated method stub
				System.out.println("Observable thread is:-" + Thread.currentThread().getName());
				return 5;
			}
		}).filter(new Predicate<Integer>() {

			@Override
			public boolean test(Integer value) throws Exception {
				// TODO Auto-generated method stub
				System.out.println("operator thread is:-" + Thread.currentThread().getName());
				return value > 10;
			}
		}).defaultIfEmpty(1).subscribe(new Observer<Integer>() {

			@Override
			public void onComplete() {
				// TODO Auto-generated method stub
				System.out.println("sequence completed successfully");
			}

			@Override
			public void onError(Throwable throwable) {
				// TODO Auto-generated method stub
				System.out.println(throwable.getMessage());

			}

			@Override
			public void onNext(Integer item) {
				// TODO Auto-generated method stub
				System.out.println("Subscriber thread:-"+Thread.currentThread().getName());
				System.out.println("emitted:-" + item);
			}

			@Override
			public void onSubscribe(Disposable disposable) {
				// TODO Auto-generated method stub

			}
		});

		
		
		 try {
		 Thread.sleep(10);
		 } catch (InterruptedException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }



		// operator and subscriber on different thread
//		Observable.fromCallable(new Callable() {
//
//			@Override
//			public Object call() throws Exception {
//				// TODO Auto-generated method stub
//				System.out.println("thread is:-" + Thread.currentThread().getName());
//				// Thread.sleep(1000);
//				System.out.println("thread is:-" + Thread.currentThread().getName());
//				return 5;
//			}
//		}).filter(new Predicate<Integer>() {
//
//			@Override
//			public boolean test(Integer value) throws Exception {
//				// TODO Auto-generated method stub
//				System.out.println("thread is:-" + Thread.currentThread().getName());
//				return value > 10;
//			}
//		}).observeOn(Schedulers.newThread()).defaultIfEmpty(1).observeOn(Schedulers.newThread()).subscribe(item -> {
//			System.out.println("thread is:-" + Thread.currentThread().getName());
//			System.out.println(item);
//		});
//
//		try {
//			Thread.sleep(10);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

}
