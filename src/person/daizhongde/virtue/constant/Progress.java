package person.daizhongde.virtue.constant;

/**
 * progress msg
 * @author dzd
 *
 */
public class Progress {

	private int progress;
	
	private String msg;

	public Progress(int progress, String msg) {
		super();
		this.progress = progress;
		this.msg = msg;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
}
