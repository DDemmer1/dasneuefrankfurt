package Cheiron.Processor.Custom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;

import com.google.common.collect.Lists;

import Cheiron.Processor.Processor;
import de.julielab.jcore.types.EntityMention;
import de.julielab.jcore.types.Lemma;
import de.julielab.jcore.types.Token;
import de.uk.spinfo.types.EntityFrequency;
import de.uk.spinfo.types.LemmaFrequency;
import de.uk.spinfo.types.TokenFrequency;

public class Frequencer extends Processor {

	private int count = 0;

	private void tokenCount(JCas cas) throws Exception {
		JCas view = null;
		Iterator<JCas> it = cas.getViewIterator();

		while (it.hasNext())
			if (!(view = it.next()).getViewName().equals("_InitialView"))
				count += JCasUtil.select(view, Token.class).size();
	}
	
	private void frequenceToken(JCas view) {
		int length = 0;

		List<Token> tokens = null;
		FSArray features = null;
		TokenFrequency frequency = null;

		Set<String> tokenHash = new HashSet<String>();
		List<Token> tokenList = new ArrayList<Token>();
		List<String> tokenStrings = new ArrayList<String>();

		for (Token t : JCasUtil.select(view, Token.class)) {
			tokenList.add(t);
			tokenStrings.add(t.getCoveredText());
		}

		tokenHash.addAll(tokenStrings);

		for (String s : tokenHash) {
			length = Collections.frequency(tokenStrings, s);

			frequency = new TokenFrequency(view);
			frequency.setBegin(0);
			frequency.setEnd(view.getDocumentText().length());
			frequency.setAbsoluteFreq(length);
			frequency.setRelativeFreq((double) length / count);
			frequency.setComponentId("Custom.Frequencer");

			features = new FSArray(view, length);
			tokens = tokenList.stream().filter(t -> t.getCoveredText().equals(s)).collect(Collectors.toList());
			tokens = Lists.reverse(tokens);

			for (Token t : tokens)
				features.set(--length, t);

			frequency.setValue(s);
			frequency.setToken(features);
			frequency.addToIndexes();

			System.out.println("Custom.Frequencer(Token): " + s + "/" + frequency.getAbsoluteFreq() + "/"
					+ frequency.getRelativeFreq());
		}
	}

	private void frequenceLemma(JCas view) {
		int length = 0;

		List<Lemma> lemmas = null;
		FSArray features = null;
		LemmaFrequency frequency = null;

		Set<String> lemmaHash = new HashSet<String>();
		ArrayList<Lemma> lemmaList = new ArrayList<Lemma>();
		ArrayList<String> lemmaStrings = new ArrayList<String>();

		for (Lemma l : JCasUtil.select(view, Lemma.class)) {
			lemmaList.add(l);
			lemmaStrings.add(l.getCoveredText());
		}

		lemmaHash.addAll(lemmaStrings);

		for (String s : lemmaHash) {
			length = Collections.frequency(lemmaStrings, s);

			frequency = new LemmaFrequency(view);
			frequency.setBegin(0);
			frequency.setEnd(view.getDocumentText().length());
			frequency.setAbsoluteFreq(length);
			frequency.setRelativeFreq((double) length / count);
			frequency.setComponentId("Custom.Frequencer");

			features = new FSArray(view, length);
			lemmas = lemmaList.stream().filter(l -> l.getCoveredText().equals(s)).collect(Collectors.toList());
			lemmas = Lists.reverse(lemmas);

			for (Lemma l : lemmas)
				features.set(--length, l);

			frequency.setValue(lemmas.get(0).getValue());
			frequency.setLemma(features);
			frequency.addToIndexes();

			System.out.println("Custom.Frequencer(Lemma): " + s + "/" + frequency.getAbsoluteFreq() + "/"
					+ frequency.getRelativeFreq());
		}
	}

	private void frequenceEntity(JCas view) {
		int length = 0;

		List<EntityMention> entities = null;
		FSArray features = null;
		EntityFrequency frequency = null;

		Set<String> entityHash = new HashSet<String>();
		ArrayList<EntityMention> entityList = new ArrayList<EntityMention>();
		ArrayList<String> entityStrings = new ArrayList<String>();

		for (EntityMention e : JCasUtil.select(view, EntityMention.class)) {
			entityList.add(e);
			entityStrings.add(e.getCoveredText());
		}

		entityHash.addAll(entityStrings);

		for (String s : entityHash) {
			length = Collections.frequency(entityStrings, s);

			frequency = new EntityFrequency(view);
			frequency.setBegin(0);
			frequency.setEnd(view.getDocumentText().length());
			frequency.setAbsoluteFreq(length);
			frequency.setRelativeFreq((double) length / count);
			frequency.setComponentId("Custom.Frequencer");

			features = new FSArray(view, length);
			entities = entityList.stream().filter(l -> l.getCoveredText().equals(s)).collect(Collectors.toList());
			entities = Lists.reverse(entities);

			for (EntityMention e : entities)
				features.set(--length, e);

			frequency.setValue(s);
			frequency.setEntity(features);
			frequency.addToIndexes();

			System.out.println("Custom.Frequencer(Entity): " + s + "/" + frequency.getAbsoluteFreq() + "/"
					+ frequency.getRelativeFreq());
		}
	}
	
	@Override
	protected void processView(JCas view) throws Exception {
		if (count == 0)
			tokenCount(view);

		frequenceToken(view);
		frequenceLemma(view);
		frequenceEntity(view);
	}

	@Override
	protected void loadDetector(String lang) throws Exception {
		return;
	}

}
