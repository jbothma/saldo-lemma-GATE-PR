package uk.co.jbothma.gate.saldo;

import gate.Annotation;
import gate.AnnotationSet;
import gate.creole.ExecutionException;
import gate.creole.ANNIEConstants;

import java.util.Iterator;

public class Lemma extends gate.creole.AbstractLanguageAnalyser {

	private String inputASName, outputASName;

	public String getInputASName() {
		return inputASName;
	}

	public void setInputASName(String inputASname) {
		this.inputASName = inputASname;
	}

	public String getOutputASName() {
		return outputASName;
	}

	public void setOutputASName(String outputASname) {
		this.outputASName = outputASname;
	}

	public void execute() throws ExecutionException {
		AnnotationSet inputAnnSet, outputAnnSet ;
		gate.Document doc;
		Iterator<Annotation> tokenIterator;
		String word;
		
		doc = getDocument();

		inputAnnSet = doc.getAnnotations(inputASName);
		outputAnnSet = doc.getAnnotations(outputASName);
		
		tokenIterator = inputAnnSet.get(ANNIEConstants.TOKEN_ANNOTATION_TYPE).iterator();

		while (tokenIterator.hasNext()) {
			Annotation tokenAnnotation = (Annotation) tokenIterator.next();
			if (tokAnnotIsWord(tokenAnnotation)) {
					word = tokAnnotString(tokenAnnotation);
			}
		}
	}
	
	private static boolean tokAnnotIsWord(Annotation tokAnnot) {
		return tokAnnot.getFeatures().get(ANNIEConstants.TOKEN_KIND_FEATURE_NAME).equals("word");
	}
	
	private static String tokAnnotString(Annotation tokAnnot) {
		return (String) tokAnnot.getFeatures().get(ANNIEConstants.TOKEN_STRING_FEATURE_NAME);
	}
}