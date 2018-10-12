package person.daizhongde.virtue.config;

import java.util.concurrent.Semaphore;

import person.daizhongde.virtue.constant.INIT;

/**
 * 导入导出用信号量
 * <p>
 * level1  level2   level3
 * 1W~5W   5W~10W   10W~15W
 * 
 * only level3
 * 4GB memory suggest number: 
 * 4   light  --because the os and application ocupy basic memory
 * 6   heavy
 * 
 * 8GB memory suggest number: 
 * 10   light
 * 12   heavy
 * 
 * 16GB memory suggest number:
 * 20   light
 * 22   heavy
 * 
 * many possible 
 * 
 * 4GB memory suggest number: 
 * 8,  4,  2   light  --because the os and application ocupy basic memory
 * 12, 6,  3   heavy
 * 
 * 8GB memory suggest number: 
 * 20, 10, 5   light
 * 24, 12, 6   heavy
 * 
 * 16GB memory suggest number:
 * 40,  20, 10   light
 * 44,  22, 11   heavy
 * 
 * 
 * @author dzd
 *
 */
public class IESemaphore{

	/** count: export record count
	 * 	size: import file size
	 *  eg: 1W < count <= 5W(export)	5MB < size <= 15MB(import) */
	public static Semaphore level1 = new Semaphore(INIT.IELevel1SemaphoreCount);
	/** eg: 5W < count <= 10W			15MB < size <= 30MB */
	public static Semaphore level2 = new Semaphore(INIT.IELevel2SemaphoreCount);
	/** eg: 10W < count <= 15W			30MB < size <= 60MB */
	public static Semaphore level3 = new Semaphore(INIT.IELevel3SemaphoreCount);
		
}
