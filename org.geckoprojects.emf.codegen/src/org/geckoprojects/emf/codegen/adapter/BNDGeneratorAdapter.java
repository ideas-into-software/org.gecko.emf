package org.geckoprojects.emf.codegen.adapter;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapter;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;

/**
 * EMF codegen generator adapter that is responsible to generate an adequate Bnd-project setup with
 * natures, builders and bnd.bnd file
 * @author Mark Hoffmann
 * @since 25.07.2017
 */
public class BNDGeneratorAdapter extends GenModelGeneratorAdapter {

	public BNDGeneratorAdapter(GeneratorAdapterFactory generatorAdapterFactory) {
		super(generatorAdapterFactory);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapter#generateModel(java.lang.Object, org.eclipse.emf.common.util.Monitor)
	 */
	@Override
	protected Diagnostic generateModel(Object object, Monitor monitor)
	{
		monitor.beginTask("", 7);

		return Diagnostic.OK_INSTANCE;
	}
}
