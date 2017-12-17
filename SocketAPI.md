##服务器监听
1.P2加入房间

	 Join
	 返回P1：JoinACK，p1positon，p2positon
	 返回P2：JoinACK，p1positon，p2positon
	
2.P1选择飞机移动
	 
	MoveLFET/MoveRIGHT
	返回P1：P1Move position/1,2,3,4
	返回P2：P1Move position/1,2,3,4
	
3.P2选择飞机移动
	MoveLFET/MoveRIGHT 
	返回P1：P2Move location/1,2,3,4
	返回P2：P2Move position/1,2,3,4
4.P1发起开始游戏

	AskStart
	返回P1：Start! 
	返回P2：Start!

5.游戏数据
	返回P1： GAME，score,n
		    x,y,w,h,number
	返回P2：GAME
	
	