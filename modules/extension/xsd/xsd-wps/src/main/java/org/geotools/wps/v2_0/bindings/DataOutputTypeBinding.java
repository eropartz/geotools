package org.geotools.wps.v2_0.bindings;

import javax.xml.namespace.QName;
import net.opengis.wps20.DataOutputType;
import net.opengis.wps20.Wps20Factory;
import org.geotools.wps.v2_0.WPS;
import org.geotools.xsd.AbstractComplexEMFBinding;

/**
 * Binding object for the type http://www.opengis.net/wps/2.0:DataOutputType.
 *
 * <p>
 *
 * <pre>
 *  <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;complexType name="DataOutputType" xmlns="http://www.w3.org/2001/XMLSchema"&gt;
 *
 *  		&lt;annotation&gt;
 *
 *  			&lt;documentation&gt;
 *
 *  				This type describes a process output in the execute response.
 *
 *  			&lt;/documentation&gt;
 *
 *  		&lt;/annotation&gt;
 *
 *  		&lt;sequence&gt;
 *
 *  			&lt;choice&gt;
 *
 *  				&lt;element ref="wps:Data"/&gt;
 *
 *  				&lt;element ref="wps:Reference"/&gt;
 *
 *  				&lt;element name="Output" type="wps:DataOutputType"/&gt;
 *
 *  			&lt;/choice&gt;
 *
 *  		&lt;/sequence&gt;
 *
 *  		&lt;attribute name="id" type="anyURI" use="required"&gt;
 *
 *  			&lt;annotation&gt;
 *
 *  				&lt;documentation&gt;
 *
 *  					Identifier of this output.
 *
 *  				&lt;/documentation&gt;
 *
 *  			&lt;/annotation&gt;
 *
 *  		&lt;/attribute&gt;
 *
 *  	&lt;/complexType&gt;
 *
 *   </code>
 *  </pre>
 *
 * @generated
 */
public class DataOutputTypeBinding extends AbstractComplexEMFBinding {

    public DataOutputTypeBinding(Wps20Factory factory) {
        super(factory);
    }
    /** @generated */
    public QName getTarget() {
        return WPS.DataOutputType;
    }

    /**
     *
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated modifiable
     */
    public Class getType() {
        return DataOutputType.class;
    }
}
