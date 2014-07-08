/**
 * 
 */
package sa.data;

/**
 * @author user
 *
 */
public enum BundleType {
	LineBundle(1),RingBundle(2);
	
	BundleType(int type){
	}
	
	public static int getType(BundleType type){
		if (type.name().equalsIgnoreCase(LineBundle.name())){
			return 1;
		}
		else {
			return 2;
		}
	}
	
	public static BundleType getType (int n){
		switch (n){
			case 1:
				return BundleType.LineBundle;
			default:
				return BundleType.RingBundle;
		}
	}

}
