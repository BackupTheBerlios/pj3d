import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.Primitive;

public class Pj3dPickable extends Pj3dToolbox
{
	private Pj3d parent;
	private BranchGroup primitiveBranch;
	private Pj3dToolbox ptools = new Pj3dToolbox();
	private float xdim, ydim, zdim;
	public Pj3dTransform transform;
	public Pj3dShader shader;
	
	// hier wird die aus dem picking resultierende branchgroup in ein Pj3d kompatibles objekt konvertiert
	
	///
	/// konstruktor 1: setzt das objekt auf den "null" punkt
	///
	public Pj3dPickable(Pj3d parent, BranchGroup pickedBranch)
	{
		this.parent = parent;
		this.primitiveBranch = pickedBranch;
		this.transform = new Pj3dTransform();
		this.shader = new Pj3dShader(parent);
		this.ParsePrimitive();
	}
	
	private void ParsePrimitive()
	{	
		transform.transformgroup = (TransformGroup)primitiveBranch.getChild(0);
		transform.transform3D = new Transform3D();
		transform.transformgroup.getTransform(transform.transform3D);
		transform.transformVector = new Vector3f(0f, 0f, 0f);
		transform.transform3D.get(transform.transformVector);
		Primitive prim = (Primitive)transform.transformgroup.getChild(0);
		shader.appearance = prim.getAppearance();
	}
}
