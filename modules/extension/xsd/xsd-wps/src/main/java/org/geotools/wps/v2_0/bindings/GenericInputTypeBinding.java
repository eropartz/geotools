package org.geotools.wps.v2_0.bindings;

import javax.xml.namespace.QName;
import net.opengis.wps20.GenericInputType;
import net.opengis.wps20.Wps20Factory;
import org.geotools.wps.v2_0.WPS;
import org.geotools.xsd.AbstractComplexEMFBinding;

/**
 * Binding object for the type http://www.opengis.net/wps/2.0:GenericInputType.
 *
 * <p>
 *
 * <pre>
 *  <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;complexType name="GenericInputType" xmlns="http://www.w3.org/2001/XMLSchema"&gt;
 *
 *  		&lt;annotation&gt;
 *
 *  			&lt;documentation&gt;Description of an input to a process. &lt;/documentation&gt;
 *
 *  		&lt;/annotation&gt;
 *
 *  		&lt;complexContent&gt;
 *
 *  			&lt;extension base="wps:DescriptionType"&gt;
 *
 *  				&lt;annotation&gt;
 *
 *  					&lt;documentation&gt;
 *
 *  						In this use, the DescriptionType shall describe a process input.
 *
 *  					&lt;/documentation&gt;
 *
 *  				&lt;/annotation&gt;
 *
 *  				&lt;sequence&gt;
 *
 *  					&lt;element maxOccurs="unbounded" minOccurs="0" name="Input" type="wps:GenericInputType"/&gt;
 *
 *  				&lt;/sequence&gt;
 *
 *  				&lt;attributeGroup ref="occurs"&gt;
 *
 *  					&lt;annotation&gt;
 *
 *  						&lt;documentation&gt;
 *
 *  							This attribute defines the cardinality of a particular input.
 *
 *  						&lt;/documentation&gt;
 *
 *  					&lt;/annotation&gt;
 *
 *  				&lt;/attributeGroup&gt;
 *
 *  			&lt;/extension&gt;
 *
 *  		&lt;/complexContent&gt;
 *
 *  	&lt;/complexType&gt;
 *
 *   </code>
 *  </pre>
 *
 * @generated
 */
public class GenericInputTypeBinding extends AbstractComplexEMFBinding {

    public GenericInputTypeBinding(Wps20Factory factory) {
        super(factory);
    }

    /** @generated */
    public QName getTarget() {
        return WPS.GenericInputType;
    }

    /**
     *
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated modifiable
     */
    public Class getType() {
        return GenericInputType.class;
    }
}
