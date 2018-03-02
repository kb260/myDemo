/**
 * Project Name:Android_Car_Example
 * File Name:OnLocationGetListener.java
 * Package Name:com.amap.api.car.example
 * Date:2015年4月2日下午6:17:17
 */

package com.panda.sharebike.lib;

/**
 * ClassName:OnLocationGetListener <br/>
 * Function: 逆地理编码或者定位完成后回调接口<br/>
 * Date:     2015年4月2日 下午6:17:17 <br/>
 *
 * @author yiyi.qi
 * @see
 * @since JDK 1.6
 */
public interface OnLocationGetListener {

    public void onLocationGet(PositionEntity entity);

    public void onRegecodeGet(PositionEntity entity);//自然定位

    public void onRegecodeGetBike(PositionEntity entity);//车的定位
}
  
