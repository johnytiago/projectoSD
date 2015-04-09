
package pt.ulisboa.tecnico.sdis.id.ws;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "AuthReqFailed", targetNamespace = "urn:pt:ulisboa:tecnico:sdis:id:ws")
public class AuthReqFailed_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private AuthReqFailed faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public AuthReqFailed_Exception(String message, AuthReqFailed faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public AuthReqFailed_Exception(String message, AuthReqFailed faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: pt.ulisboa.tecnico.sdis.id.ws.AuthReqFailed
     */
    public AuthReqFailed getFaultInfo() {
        return faultInfo;
    }

}
