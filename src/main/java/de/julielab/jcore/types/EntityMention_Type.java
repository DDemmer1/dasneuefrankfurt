
/* First created by JCasGen Fri Jul 22 13:16:57 CEST 2016 */
package de.julielab.jcore.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** (Named) EntityMention (i.e. An entity is an object or set of objects in the world. Entitiy mentions may be refrenced in a text by their name, indicated by a common noun or noun phrase, or represented by a pronoun) annotation
 * Updated by JCasGen Fri Jul 22 13:16:57 CEST 2016
 * @generated */
public class EntityMention_Type extends ConceptMention_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (EntityMention_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = EntityMention_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new EntityMention(addr, EntityMention_Type.this);
  			   EntityMention_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new EntityMention(addr, EntityMention_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = EntityMention.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.julielab.jcore.types.EntityMention");
 
  /** @generated */
  final Feature casFeat_head;
  /** @generated */
  final int     casFeatCode_head;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getHead(int addr) {
        if (featOkTst && casFeat_head == null)
      jcas.throwFeatMissing("head", "de.julielab.jcore.types.EntityMention");
    return ll_cas.ll_getRefValue(addr, casFeatCode_head);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setHead(int addr, int v) {
        if (featOkTst && casFeat_head == null)
      jcas.throwFeatMissing("head", "de.julielab.jcore.types.EntityMention");
    ll_cas.ll_setRefValue(addr, casFeatCode_head, v);}
    
  
 
  /** @generated */
  final Feature casFeat_mentionLevel;
  /** @generated */
  final int     casFeatCode_mentionLevel;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getMentionLevel(int addr) {
        if (featOkTst && casFeat_mentionLevel == null)
      jcas.throwFeatMissing("mentionLevel", "de.julielab.jcore.types.EntityMention");
    return ll_cas.ll_getStringValue(addr, casFeatCode_mentionLevel);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setMentionLevel(int addr, String v) {
        if (featOkTst && casFeat_mentionLevel == null)
      jcas.throwFeatMissing("mentionLevel", "de.julielab.jcore.types.EntityMention");
    ll_cas.ll_setStringValue(addr, casFeatCode_mentionLevel, v);}
    
  
 
  /** @generated */
  final Feature casFeat_entityString;
  /** @generated */
  final int     casFeatCode_entityString;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getEntityString(int addr) {
        if (featOkTst && casFeat_entityString == null)
      jcas.throwFeatMissing("entityString", "de.julielab.jcore.types.EntityMention");
    return ll_cas.ll_getRefValue(addr, casFeatCode_entityString);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setEntityString(int addr, int v) {
        if (featOkTst && casFeat_entityString == null)
      jcas.throwFeatMissing("entityString", "de.julielab.jcore.types.EntityMention");
    ll_cas.ll_setRefValue(addr, casFeatCode_entityString, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public String getEntityString(int addr, int i) {
        if (featOkTst && casFeat_entityString == null)
      jcas.throwFeatMissing("entityString", "de.julielab.jcore.types.EntityMention");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_entityString), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_entityString), i);
	return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_entityString), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setEntityString(int addr, int i, String v) {
        if (featOkTst && casFeat_entityString == null)
      jcas.throwFeatMissing("entityString", "de.julielab.jcore.types.EntityMention");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_entityString), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_entityString), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_entityString), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public EntityMention_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_head = jcas.getRequiredFeatureDE(casType, "head", "de.julielab.jcore.types.Annotation", featOkTst);
    casFeatCode_head  = (null == casFeat_head) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_head).getCode();

 
    casFeat_mentionLevel = jcas.getRequiredFeatureDE(casType, "mentionLevel", "uima.cas.String", featOkTst);
    casFeatCode_mentionLevel  = (null == casFeat_mentionLevel) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_mentionLevel).getCode();

 
    casFeat_entityString = jcas.getRequiredFeatureDE(casType, "entityString", "uima.cas.StringArray", featOkTst);
    casFeatCode_entityString  = (null == casFeat_entityString) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_entityString).getCode();

  }
}



    