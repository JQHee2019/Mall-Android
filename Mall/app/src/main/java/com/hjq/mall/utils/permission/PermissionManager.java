package com.hjq.mall.utils.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

public class PermissionManager {

    private String storage = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private String camera = Manifest.permission.CAMERA;

    private final int requestcode_camera=1;
    private static PermissionManager permissionManager;
    private RequestPermission requestPermission;
    public static PermissionManager getInstance(){
        if(null==permissionManager){
            permissionManager=new PermissionManager();
        }
        return permissionManager;
    }

    //照相机和文件权限
    public void getCameraPermission(Context context, RequestPermission requestPermission){
        this.requestPermission=requestPermission;
        PermissionUtil.getInstance().checkPermission(context,new String[]{storage,camera},requestcode_camera);
    }



    /**
     * 一个或多个权限请求结果回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public void onRequestPermissionsResult(Activity context, int requestCode, String[] permissions, int[] grantResults) {
        if(requestPermission==null){
            return;
        }
        boolean hasAllGranted = true;
        for (int i = 0; i < grantResults.length; ++i) {
            if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                hasAllGranted = false;
                // 可以推断出用户选择了“不在提示”选项，在这种情况下需要引导用户至设置页手动授权
                if (!ActivityCompat.shouldShowRequestPermissionRationale(context, permissions[i])) {
                    requestPermission.never();
                } else {
                    //权限请求失败，但未选中“不再提示”选项
                    requestPermission.refuse();
                }
                break;
            }
        }
        if (hasAllGranted) {
            //权限请求成功
            requestPermission.isOk();
        }
    }

    public interface  RequestPermission{
        public void isOk(); // 同意授权
        public void never(); // 不再提示，需要手动授权
        public void refuse(); // //权限请求失败，但未选中“不再提示”选项
    }

}

/*
PermissionManager.getInstance().getCameraPermission(getContext(), new PermissionManager.RequestPermission() {
                   @Override
                   public void isOk() {
                       Log.e("isOK","++++++++++++++++++++++++++++++++++++");
                   }
                   @Override
                   public void never() {
                       Log.e("never","++++++++++++++++++++++++++++++++++++");
                   }
                   @Override
                   public void refuse() {
                       Log.e("refuse","++++++++++++++++++++++++++++++++++++");
                   }
               });

*/
