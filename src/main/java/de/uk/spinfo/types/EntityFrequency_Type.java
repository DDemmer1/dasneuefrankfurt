
/* First created by JCasGen Wed Aug 03 19:04:06 CEST 2016 */
package de.uk.spinfo.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.DocumentAnnotation_Type;

/** 
 * Updated by JCasGen Fri Aug 12 13:58:22 CEST 2016
 * @generated */
public class EntityFrequency_Type extends DocumentAnnotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (EntityFrequency_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = EntityFrequency_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new EntityFrequency(addr, EntityFrequency_Type.this);
  			   EntityFrequency_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new EntityFrequency(addr, EntityFrequency_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = EntityFrequency.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.uk.spinfo.types.EntityFrequency");
 
  /** @generated */
  final Feature casFeat_entity;
  /** @generated */
  final int     casFeatCode_entity;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getEntity(int addr) {
        if (featOkTst && casFeat_entity == null)
      jcas.throwFeatMissing("entity", "de.uk.spinfo.types.EntityFrequency");
    return ll_cas.ll_getRefValue(addr, casFeatCode_entity);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setEntity(int addr, int v) {
        if (featOkTst && casFeat_entity == null)
      jcas.throwFeatMissing("entity", "de.uk.spinfo.types.EntityFrequency");
    ll_cas.ll_setRefValue(addr, casFeatCode_entity, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public int getEntity(int addr, int i) {
        if (featOkTst && casFeat_entity == null)
      jcas.throwFeatMissing("entity", "de.uk.spinfo.types.EntityFrequency");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_entity), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_entity), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_entity), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setEntity(int addr, int i, int v) {
        if (featOkTst && casFeat_entity == null)
      jcas.throwFeatMissing("entity", "de.uk.spinfo.types.EntityFrequency");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_entity), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_entity), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_entity), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_value;
  /** @generated */
  final int     casFeatCode_value;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getValue(int addr) {
        if (featOkTst && casFeat_value == null)
      jcas.throwFeatMissing("value", "de.uk.spinfo.types.EntityFrequency");
    return ll_cas.ll_getStringValue(addr, casFeatCode_value);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setValue(int addr, String v) {
        if (featOkTst && casFeat_value == null)
      jcas.throwFeatMissing("value", "de.uk.spinfo.types.EntityFrequency");
    ll_cas.ll_setStringValue(addr, casFeatCode_value, v);}
    
  
 
  /** @generated */
  final Feature casFeat_absoluteFreq;
  /** @generated */
  final int     casFeatCode_absoluteFreq;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getAbsoluteFreq(int addr) {
        if (featOkTst && casFeat_absoluteFreq == null)
      jcas.throwFeatMissing("absoluteFreq", "de.uk.spinfo.types.EntityFrequency");
    return ll_cas.ll_getIntValue(addr, casFeatCode_absoluteFreq);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAbsoluteFreq(int addr, int v) {
        if (featOkTst && casFeat_absoluteFreq == null)
      jcas.throwFeatMissing("absoluteFreq", "de.uk.spinfo.types.EntityFrequency");
    ll_cas.ll_setIntValue(addr, casFeatCode_absoluteFreq, v);}
    
  
 
  /** @generated */
  final Feature casFeat_relativeFreq;
  /** @generated */
  final int     casFeatCode_relativeFreq;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public double getRelativeFreq(int addr) {
        if (featOkTst && casFeat_relativeFreq == null)
      jcas.throwFeatMissing("relativeFreq", "de.uk.spinfo.types.EntityFrequency");
    return ll_cas.ll_getDoubleValue(addr, casFeatCode_relativeFreq);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setRelativeFreq(int addr, double v) {
        if (featOkTst && casFeat_relativeFreq == null)
      jcas.throwFeatMissing("relativeFreq", "de.uk.spinfo.types.EntityFrequency");
    ll_cas.ll_setDoubleValue(addr, casFeatCode_relativeFreq, v);}
    
  
 
  /** @generated */
  final Feature casFeat_componentId;
  /** @generated */
  final int     casFeatCode_componentId;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getComponentId(int addr) {
        if (featOkTst && casFeat_componentId == null)
      jcas.throwFeatMissing("componentId", "de.uk.spinfo.types.EntityFrequency");
    return ll_cas.ll_getStringValue(addr, casFeatCode_componentId);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setComponentId(int addr, String v) {
        if (featOkTst && casFeat_componentId == null)
      jcas.throwFeatMissing("componentId", "de.uk.spinfo.types.EntityFrequency");
    ll_cas.ll_setStringValue(addr, casFeatCode_componentId, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public EntityFrequency_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_entity = jcas.getRequiredFeatureDE(casType, "entity", "uima.cas.FSArray", featOkTst);
    casFeatCode_entity  = (null == casFeat_entity) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_entity).getCode();

 
    casFeat_value = jcas.getRequiredFeatureDE(casType, "value", "uima.cas.String", featOkTst);
    casFeatCode_value  = (null == casFeat_value) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_value).getCode();

 
    casFeat_absoluteFreq = jcas.getRequiredFeatureDE(casType, "absoluteFreq", "uima.cas.Integer", featOkTst);
    casFeatCode_absoluteFreq  = (null == casFeat_absoluteFreq) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_absoluteFreq).getCode();

 
    casFeat_relativeFreq = jcas.getRequiredFeatureDE(casType, "relativeFreq", "uima.cas.Double", featOkTst);
    casFeatCode_relativeFreq  = (null == casFeat_relativeFreq) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_relativeFreq).getCode();

 
    casFeat_componentId = jcas.getRequiredFeatureDE(casType, "componentId", "uima.cas.String", featOkTst);
    casFeatCode_componentId  = (null == casFeat_componentId) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_componentId).getCode();

  }
}



    