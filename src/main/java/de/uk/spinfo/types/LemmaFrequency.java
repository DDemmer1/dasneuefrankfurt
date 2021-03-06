

/* First created by JCasGen Fri Jul 22 13:16:57 CEST 2016 */
package de.uk.spinfo.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.DocumentAnnotation;
import de.julielab.jcore.types.Lemma;


/** 
 * Updated by JCasGen Fri Aug 12 13:58:22 CEST 2016
 * XML source: /home/phil/Documents/Spinfo/Prometheus/Cheiron/desc/spinfo-cheiron-types.xml
 * @generated */
public class LemmaFrequency extends DocumentAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(LemmaFrequency.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected LemmaFrequency() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public LemmaFrequency(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public LemmaFrequency(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public LemmaFrequency(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: lemma

  /** getter for lemma - gets 
   * @generated
   * @return value of the feature 
   */
  public FSArray getLemma() {
    if (LemmaFrequency_Type.featOkTst && ((LemmaFrequency_Type)jcasType).casFeat_lemma == null)
      jcasType.jcas.throwFeatMissing("lemma", "de.uk.spinfo.types.LemmaFrequency");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((LemmaFrequency_Type)jcasType).casFeatCode_lemma)));}
    
  /** setter for lemma - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setLemma(FSArray v) {
    if (LemmaFrequency_Type.featOkTst && ((LemmaFrequency_Type)jcasType).casFeat_lemma == null)
      jcasType.jcas.throwFeatMissing("lemma", "de.uk.spinfo.types.LemmaFrequency");
    jcasType.ll_cas.ll_setRefValue(addr, ((LemmaFrequency_Type)jcasType).casFeatCode_lemma, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for lemma - gets an indexed value - 
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public Lemma getLemma(int i) {
    if (LemmaFrequency_Type.featOkTst && ((LemmaFrequency_Type)jcasType).casFeat_lemma == null)
      jcasType.jcas.throwFeatMissing("lemma", "de.uk.spinfo.types.LemmaFrequency");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((LemmaFrequency_Type)jcasType).casFeatCode_lemma), i);
    return (Lemma)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((LemmaFrequency_Type)jcasType).casFeatCode_lemma), i)));}

  /** indexed setter for lemma - sets an indexed value - 
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setLemma(int i, Lemma v) { 
    if (LemmaFrequency_Type.featOkTst && ((LemmaFrequency_Type)jcasType).casFeat_lemma == null)
      jcasType.jcas.throwFeatMissing("lemma", "de.uk.spinfo.types.LemmaFrequency");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((LemmaFrequency_Type)jcasType).casFeatCode_lemma), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((LemmaFrequency_Type)jcasType).casFeatCode_lemma), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: value

  /** getter for value - gets 
   * @generated
   * @return value of the feature 
   */
  public String getValue() {
    if (LemmaFrequency_Type.featOkTst && ((LemmaFrequency_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "de.uk.spinfo.types.LemmaFrequency");
    return jcasType.ll_cas.ll_getStringValue(addr, ((LemmaFrequency_Type)jcasType).casFeatCode_value);}
    
  /** setter for value - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setValue(String v) {
    if (LemmaFrequency_Type.featOkTst && ((LemmaFrequency_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "de.uk.spinfo.types.LemmaFrequency");
    jcasType.ll_cas.ll_setStringValue(addr, ((LemmaFrequency_Type)jcasType).casFeatCode_value, v);}    
   
    
  //*--------------*
  //* Feature: absoluteFreq

  /** getter for absoluteFreq - gets 
   * @generated
   * @return value of the feature 
   */
  public int getAbsoluteFreq() {
    if (LemmaFrequency_Type.featOkTst && ((LemmaFrequency_Type)jcasType).casFeat_absoluteFreq == null)
      jcasType.jcas.throwFeatMissing("absoluteFreq", "de.uk.spinfo.types.LemmaFrequency");
    return jcasType.ll_cas.ll_getIntValue(addr, ((LemmaFrequency_Type)jcasType).casFeatCode_absoluteFreq);}
    
  /** setter for absoluteFreq - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setAbsoluteFreq(int v) {
    if (LemmaFrequency_Type.featOkTst && ((LemmaFrequency_Type)jcasType).casFeat_absoluteFreq == null)
      jcasType.jcas.throwFeatMissing("absoluteFreq", "de.uk.spinfo.types.LemmaFrequency");
    jcasType.ll_cas.ll_setIntValue(addr, ((LemmaFrequency_Type)jcasType).casFeatCode_absoluteFreq, v);}    
   
    
  //*--------------*
  //* Feature: relativeFreq

  /** getter for relativeFreq - gets 
   * @generated
   * @return value of the feature 
   */
  public double getRelativeFreq() {
    if (LemmaFrequency_Type.featOkTst && ((LemmaFrequency_Type)jcasType).casFeat_relativeFreq == null)
      jcasType.jcas.throwFeatMissing("relativeFreq", "de.uk.spinfo.types.LemmaFrequency");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((LemmaFrequency_Type)jcasType).casFeatCode_relativeFreq);}
    
  /** setter for relativeFreq - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setRelativeFreq(double v) {
    if (LemmaFrequency_Type.featOkTst && ((LemmaFrequency_Type)jcasType).casFeat_relativeFreq == null)
      jcasType.jcas.throwFeatMissing("relativeFreq", "de.uk.spinfo.types.LemmaFrequency");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((LemmaFrequency_Type)jcasType).casFeatCode_relativeFreq, v);}    
   
    
  //*--------------*
  //* Feature: componentId

  /** getter for componentId - gets 
   * @generated
   * @return value of the feature 
   */
  public String getComponentId() {
    if (LemmaFrequency_Type.featOkTst && ((LemmaFrequency_Type)jcasType).casFeat_componentId == null)
      jcasType.jcas.throwFeatMissing("componentId", "de.uk.spinfo.types.LemmaFrequency");
    return jcasType.ll_cas.ll_getStringValue(addr, ((LemmaFrequency_Type)jcasType).casFeatCode_componentId);}
    
  /** setter for componentId - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setComponentId(String v) {
    if (LemmaFrequency_Type.featOkTst && ((LemmaFrequency_Type)jcasType).casFeat_componentId == null)
      jcasType.jcas.throwFeatMissing("componentId", "de.uk.spinfo.types.LemmaFrequency");
    jcasType.ll_cas.ll_setStringValue(addr, ((LemmaFrequency_Type)jcasType).casFeatCode_componentId, v);}    
  }

    