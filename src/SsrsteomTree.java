import java.util.ArrayList;
import java.util.HashSet;


public class SsrsteomTree {
	private ArrayList<ArrayList<Double>> ssrsteomList=new ArrayList<ArrayList<Double>>();
	private ArrayList<Double> coeffecientList=new ArrayList<Double>();
	private Double TermintationEntity;
	private double coeffecientOfHighestDegree;
	private int highestLevel;
	private double highestDegree;
	private int deltax=1;

	public SsrsteomTree(ArrayList<Double> ssrsteomInitialRow) throws Exception
	{
		GenTree(ssrsteomInitialRow);
		PopulatInformation();
	}
	private int factorial(double d)
	{
		int counter=1;
		for(int i=1;i<=d;i++)
		{
			counter*=i;
		}
		return counter;
	}
	/*
	 * sets initialization variable
	 * sets the highestDegree of the ssrsteomtree
	 * sets the coeffecientOfHighestDegree of the ssrsteomtree
	 * */
	private void PopulatInformation() 
	{
		//grab last element in last list in ssrsteomList
		setHighestDegree(highestLevel-1);
		setCoeffecientOfHighestDegree(getTermintationEntity()/(deltax*Math.pow(deltax, getHighestDegree())*factorial(getHighestDegree())));
	}

	/**
	 * This takes the intial row and does the recursive deviation togenerate the tree
	 * @param ssrsteomRow
	 * this is the initial output ArrayList for the Ssrsteom Tree
	 * @throws Exception
	 */
	public void GenTree(ArrayList<Double> ssrsteomRow) throws Exception
	{
		highestLevel++;
		getSsrsteomList().add(ssrsteomRow);
        HashSet<Double> uniqueVals=new HashSet<Double>(ssrsteomRow);
		if(uniqueVals.size()==0)
		{
			throw new Exception("list passed is of size zero");
		}
		else if(uniqueVals.size()==1)
		{
			setTermintationEntity(ssrsteomRow.get(0));
		}
		else
		{
			ArrayList<Double> nextRow= new ArrayList<Double>();
			for(int i=0;i<ssrsteomRow.size()-1;i++)
			{
				double deviation=ssrsteomRow.get(i+1)-ssrsteomRow.get(i);
				nextRow.add(deviation);
			}
			GenTree(nextRow);
		}
	}
	/**
	 * getHighestDegree() is calling d
	 * d is suppose to be the degree of monomial
	 * This is representing f(x)=C_d*x^d
	 * @param x
	 * this represents an input value e.g. f(2) -> 5x^2 = 5(2^2) =(4)(5) = returns 20 
	 * @return
	 */
	public double highestDegreeFunction(int x)
	{
		return getCoeffecientOfHighestDegree()*Math.pow(x, getHighestDegree());
	} 
	/**
	 * This is the method that is called on the constructed instance of SsrstoemTree
	 * to populate the coeffecientList for any given ssrsteom Tree,  a.k.a. does the
	 * interpolation  (finds the coeffecients of a given polynomial that will hit the input values)
	 * @throws Exception
	 */
	public void solve() throws Exception
	{
        getCoeffecientList().clear();
		solve(this);
	}
	/**
	 * populates the coeffecient list with the solution (the polynomial that hits the input data)
	 * */
	public void solve(SsrsteomTree localSsrsteomTree) throws Exception
	{
		//add the coeffecient of the current instace's highest degree into this.coeffecientlist
        getCoeffecientList().add(localSsrsteomTree.getCoeffecientOfHighestDegree());
        System.out.println(localSsrsteomTree);
        ArrayList<Double> SuccessivePoloynomialList=new ArrayList<Double>();
        /*
         *  populate successivePolynomialList (a list containining intial array for successive portion of the polynomial)
         *  with initial values (formed by subtracting the current instaces initial array from monimoial with thte same inputer value initial array)
         */

        //TODO;  change what is passed to highestDegreefunction with actual input values instead of i+1
        for(int i=0;i<localSsrsteomTree.getSsrsteomList().get(0).size();i++)
        {
                SuccessivePoloynomialList.add(localSsrsteomTree.getSsrsteomList().get(0).get(i)-localSsrsteomTree.highestDegreeFunction(i+1));
        }
        
        SsrsteomTree nextDegreeTree= new SsrsteomTree(SuccessivePoloynomialList);
        //account for 0 ceffecients
        double degreeDeviation=localSsrsteomTree.getHighestDegree()-nextDegreeTree.getHighestDegree();
        if(degreeDeviation>1)
        {
        	for(int i=0;i<degreeDeviation-1;i++)
        	{
        		getCoeffecientList().add(0.0);
        	}
        }

        /*
         * base case
         * if ssrsteomList is of size one (this means that ssrsteom list contains a list of a constant value e.g. [[2,2,2,2,]])
         * then run the solve method
         */
		if(localSsrsteomTree.getSsrsteomList().size()!=1)
		{
                solve(nextDegreeTree);
		}
	}

	@Override
	public String toString() {
		return "SsrsteomTree [ssrsteomList=" + getSsrsteomList()
				+ "]\nTermintationEntity=" + TermintationEntity
				+ "\ncoeffecientOfHighestDegree=" + getCoeffecientOfHighestDegree()
				+ "\nhighestLevel=" + highestLevel + "\nhighestDegree="
				+ getHighestDegree() + "\ndeltax=" + deltax;
	}
	public double getTermintationEntity() {
		return TermintationEntity;
	}
	public void setTermintationEntity(Double double1) {
		TermintationEntity = double1;
	}
	public double getHighestDegree() {
		return highestDegree;
	}
	public void setHighestDegree(int highestDegree) {
		this.highestDegree = highestDegree;
	}
	public double getCoeffecientOfHighestDegree() {
		return coeffecientOfHighestDegree;
	}
	public void setCoeffecientOfHighestDegree(double coeffecientOfHighestDegree) {
		this.coeffecientOfHighestDegree = coeffecientOfHighestDegree;
	}
	public ArrayList<ArrayList<Double>> getSsrsteomList() {
		return ssrsteomList;
	}
	public void setSsrsteomList(ArrayList<ArrayList<Double>> ssrsteomList) {
		this.ssrsteomList = ssrsteomList;
	}
	public ArrayList<Double> getCoeffecientList() {
		return coeffecientList;
	}
	public void setCoeffecientList(ArrayList<Double> coeffecientList) {
		this.coeffecientList = coeffecientList;
	}
	
}
