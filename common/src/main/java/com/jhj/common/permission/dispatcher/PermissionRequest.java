package com.jhj.common.permission.dispatcher;

/**
 * a permission request.
 */
public interface PermissionRequest {

    void proceed();

    void cancel();
}
