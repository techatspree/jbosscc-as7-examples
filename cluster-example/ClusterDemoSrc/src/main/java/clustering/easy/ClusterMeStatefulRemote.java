package clustering.easy;

import javax.ejb.Remote;

@Remote
public interface ClusterMeStatefulRemote
{
	public int getCallcounter();
	public String whoAreYou();
	public void killMe();
}
