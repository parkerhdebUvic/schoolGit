public class AutomaticCar implements Vehicle {
	private String model;
	private int distanceTraveled;
	private boolean seatbeltsOn;

	public AutomaticCar(String model, int distanceTraveled){
		this.model = model;
		this.distanceTraveled = distanceTraveled;
		seatbeltsOn = false;
	}

	public boolean safetyCheck(){
		
	}
 
	
	public void drive(int distance){

	}


	public int getKMs() {
		return distanceTraveled;
	} 

}
