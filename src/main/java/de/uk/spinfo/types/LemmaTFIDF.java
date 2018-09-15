

/* First created by JCasGen Fri Aug 12 13:58:19 CEST 2016 */
package de.uk.spinfo.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.DocumentAnnotation;


/** 
 * Updated by JCasGen Fri Aug 12 13:58:22 CEST 2016
 * XML source: /home/phil/Documents/Spinfo/Prometheus/Cheiron/desc/spinfo-cheiron-types.xml
 * @generated */
public class LemmaTFIDF extends DocumentAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(LemmaTFIDF.class);
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
  protected LemmaTFIDF() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public LemmaTFIDF(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public LemmaTFIDF(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public LemmaTFIDF(JCas jcas, int begin, int end) {
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
  //* Feature: value

  /** getter for value - gets 
   * @generated
   * @return value of the feature 
   */
  public String getValue() {
    if (LemmaTFIDF_Type.featOkTst && ((LemmaTFIDF_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "de.uk.spinfo.types.LemmaTFIDF");
    return jcasType.ll_cas.ll_getStringValue(addr, ((LemmaTFIDF_Type)jcasType).casFeatCode_value);}
    
  /** setter for value - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setValue(String v) {
    if (LemmaTFIDF_Type.featOkTst && ((LemmaTFIDF_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "de.uk.spinfo.types.LemmaTFIDF");
    jcasType.ll_cas.ll_setStringValue(addr, ((LemmaTFIDF_Type)jcasType).casFeatCode_value, v);}    
   
    
  //*--------------*
  //* Feature: tfWeight

  /** getter for tfWeight - gets 
   * @generated
   * @return value of the feature 
   */
  public double getTfWeight() {
    if (LemmaTFIDF_Type.featOkTst && ((LemmaTFIDF_Type)jcasType).casFeat_tfWeight == null)
      jcasType.jcas.throwFeatMissing("tfWeight", "de.uk.spinfo.types.LemmaTFIDF");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((LemmaTFIDF_Type)jcasType).casFeatCode_tfWeight);}
    
  /** setter for tfWeight - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTfWeight(double v) {
    if (LemmaTFIDF_Type.featOkTst && ((LemmaTFIDF_Type)jcasType).casFeat_tfWeight == null)
      jcasType.jcas.throwFeatMissing("tfWeight", "de.uk.spinfo.types.LemmaTFIDF");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((LemmaTFIDF_Type)jcasType).casFeatCode_tfWeight, v);}    
   
    
  //*--------------*
  //* Feature: tfidfWeight

  /** getter for tfidfWeight - gets 
   * @generated
   * @return value of the feature 
   */
  public double getTfidfWeight() {
    if (LemmaTFIDF_Type.featOkTst && ((LemmaTFIDF_Type)jcasType).casFeat_tfidfWeight == null)
      jcasType.jcas.throwFeatMissing("tfidfWeight", "de.uk.spinfo.types.LemmaTFIDF");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((LemmaTFIDF_Type)jcasType).casFeatCode_tfidfWeight);}
    
  /** setter for tfidfWeight - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTfidfWeight(double v) {
    if (LemmaTFIDF_Type.featOkTst && ((LemmaTFIDF_Type)jcasType).casFeat_tfidfWeight == null)
      jcasType.jcas.throwFeatMissing("tfidfWeight", "de.uk.spinfo.types.LemmaTFIDF");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((LemmaTFIDF_Type)jcasType).casFeatCode_tfidfWeight, v);}    
   
    
  //*--------------*
  //* Feature: componentId

  /** getter for componentId - gets 
   * @generated
   * @return value of the feature 
   */
  public String getComponentId() {
    if (LemmaTFIDF_Type.featOkTst && ((LemmaTFIDF_Type)jcasType).casFeat_componentId == null)
      jcasType.jcas.throwFeatMissing("componentId", "de.uk.spinfo.types.LemmaTFIDF");
    return jcasType.ll_cas.ll_getStringValue(addr, ((LemmaTFIDF_Type)jcasType).casFeatCode_componentId);}
    
  /** setter for componentId - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setComponentId(String v) {
    if (LemmaTFIDF_Type.featOkTst && ((LemmaTFIDF_Type)jcasType).casFeat_componentId == null)
      jcasType.jcas.throwFeatMissing("componentId", "de.uk.spinfo.types.LemmaTFIDF");
    jcasType.ll_cas.ll_setStringValue(addr, ((LemmaTFIDF_Type)jcasType).casFeatCode_componentId, v);}    
  }

    