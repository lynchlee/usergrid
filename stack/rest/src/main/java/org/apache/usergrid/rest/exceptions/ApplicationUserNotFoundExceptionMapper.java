package org.apache.usergrid.rest.exceptions;

import org.apache.usergrid.management.exceptions.ApplicationUserNotFoundException;

import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

/**
 * Description for Class ApplicationUserNotFoundExceptionMapper
 *
 * @author Lynch Lee<Lynch.lee9527@gmail.com>
 * @version 12/11/2016.
 */
public class ApplicationUserNotFoundExceptionMapper extends AbstractExceptionMapper<ApplicationUserNotFoundException> {

    @Override
    public Response toResponse(ApplicationUserNotFoundException e ) {
        return toResponse( NOT_FOUND, e );
    }

}
