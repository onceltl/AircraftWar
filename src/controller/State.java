package controller;


public class State {
	private static State INSTANCE = new State();
	private int state;
	private State(){
		state=0;
	}
	public boolean isStart() {
		return state==0;
	}
	public boolean isInRoom() {
		return state==1;
	}
	public boolean isInGame() {
		return state==2;
	}
	public boolean isInEnd() {
		return state==3;
	}
	public void nextstate() {
		state=(state+1)%4;
	}
	public void laststate() {
		state=state-1;
	}
	public static State getInstance() {
		return INSTANCE;
	}
}
