package controller;



import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class GameSound {
	BgSoundThread bst = null;
	//播放背景音乐的代码
	@SuppressWarnings("deprecation")
	public void playBgSound(String mp3){
		if(bst!=null){
			bst.stop();
		}
		bst = new BgSoundThread(mp3);
		bst.start();
	}
	
	//使用多线程来播放爆炸和发射子弹的声音
	public void playSound(String mp3){
		SoundThread st = new SoundThread(mp3);
		st.start();
	}
	
	//循环播放背景声音
	class BgSoundThread extends Thread{
		
		public String mp3Url;
		
		public BgSoundThread(String mp3Url) {
			this.mp3Url = mp3Url;
		}

		public void run(){
			for(;;){			
				try {
					//1. 加载MP3文件
					InputStream in=this.getClass().getResourceAsStream(mp3Url);
					//2. 根据文件流，创建播放类的对象
					AdvancedPlayer ad;
					ad = new AdvancedPlayer(in);
					ad.play();
				} catch (JavaLayerException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	//只播放一次
	class SoundThread extends Thread{
		
		public String mp3Url;
		
		public SoundThread(String mp3Url) {
			this.mp3Url = mp3Url;
		}

		public void run(){
			//测试声音的播放
			//1. 加载MP3文件
			InputStream in=this.getClass().getResourceAsStream(mp3Url);
			
			//2. 根据文件流，创建播放类的对象
			AdvancedPlayer ad;
			try {
				ad = new AdvancedPlayer(in);
				ad.play();
			} catch (JavaLayerException e) {
				e.printStackTrace();
			}
		}
	}
	
}
