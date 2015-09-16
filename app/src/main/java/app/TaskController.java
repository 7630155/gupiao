package app;

import java.text.SimpleDateFormat;
import java.util.concurrent.*;
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  
@RequestMapping("/task") 
public class TaskController {

    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();  
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
	
	@RequestMapping(value="/run", method = RequestMethod.GET)
	public @ResponseBody String runService() throws Exception{
		Runnable runnable = new Runnable() {  
			public void run() {  
				System.out.println("Hello !! at "+sdf.format(new Date()));  
			}  
		};

		//�ڶ�������Ϊ�״�ִ�е���ʱʱ�䣬����������Ϊ��ʱִ�еļ��ʱ��  
		final ScheduledFuture future1=service.scheduleAtFixedRate(runnable, 10, 1, TimeUnit.SECONDS); 
		//future1.cancel(true);
		return "ok";
	}

	@RequestMapping(value="/stop", method = RequestMethod.GET)
	public void stopService() throws Exception{
		service.shutdown();
	}


}
