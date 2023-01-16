package org.thoughtcrime.securesms.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import androidx.annotation.NonNull;

import org.signal.ringrtc.CallManager;
import org.thoughtcrime.securesms.keyvalue.SignalStore;

public final class NetworkUtil {

  private NetworkUtil() {}

  public static boolean isConnectedWifi(@NonNull Context context) {
    final NetworkInfo info = getNetworkInfo(context);
    return info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI;
  }

  public static boolean isConnectedMobile(@NonNull Context context) {
    final NetworkInfo info = getNetworkInfo(context);
    return info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE;
  }

  public static boolean isConnectedRoaming(@NonNull Context context) {
    final NetworkInfo info = getNetworkInfo(context);
    return info != null && info.isConnected() && info.isRoaming() && info.getType() == ConnectivityManager.TYPE_MOBILE;
  }

  public static @NonNull CallManager.BandwidthMode getCallingBandwidthMode(@NonNull Context context) {
    return useLowBandwidthCalling(context) ? CallManager.BandwidthMode.LOW : CallManager.BandwidthMode.NORMAL;
  }

  public static String getNetworkTypeDescriptor(@NonNull Context context) {
    NetworkInfo info = getNetworkInfo(context);
    if (info == null || !info.isConnected()) {
      return "NOT CONNECTED";
    } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
      return "WIFI";
    } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
      int networkType = info.getSubtype();
      switch (networkType) {
        case TelephonyManager.NETWORK_TYPE_GPRS:     return "MOBILE - GPRS";
        case TelephonyManager.NETWORK_TYPE_EDGE:     return "MOBILE - EDGE";
        case TelephonyManager.NETWORK_TYPE_CDMA:     return "MOBILE - CDMA";
        case TelephonyManager.NETWORK_TYPE_1xRTT:    return "MOBILE - 1xRTT";
        case TelephonyManager.NETWORK_TYPE_IDEN:     return "MOBILE - IDEN";
        case TelephonyManager.NETWORK_TYPE_GSM:      return "MOBILE - GSM";
        case TelephonyManager.NETWORK_TYPE_UMTS:     return "MOBILE - UMTS";
        case TelephonyManager.NETWORK_TYPE_EVDO_0:   return "MOBILE - EVDO_0";
        case TelephonyManager.NETWORK_TYPE_EVDO_A:   return "MOBILE - EVDO_A";
        case TelephonyManager.NETWORK_TYPE_HSDPA:    return "MOBILE - HSDPA";
        case TelephonyManager.NETWORK_TYPE_HSUPA:    return "MOBILE - HSUPA";
        case TelephonyManager.NETWORK_TYPE_HSPA:     return "MOBILE - HSPA";
        case TelephonyManager.NETWORK_TYPE_EVDO_B:   return "MOBILE - EVDO_B";
        case TelephonyManager.NETWORK_TYPE_EHRPD:    return "MOBILE - EHRDP";
        case TelephonyManager.NETWORK_TYPE_HSPAP:    return "MOBILE - HSPAP";
        case TelephonyManager.NETWORK_TYPE_TD_SCDMA: return "MOBILE - TD_SCDMA";
        case TelephonyManager.NETWORK_TYPE_LTE:      return "MOBILE - LTE";
        case TelephonyManager.NETWORK_TYPE_IWLAN:    return "MOBILE - IWLAN";
        case 19:                                     return "MOBILE - LTE_CA";
        case TelephonyManager.NETWORK_TYPE_NR:       return "MOBILE - NR";
        default:                                     return "MOBILE - OTHER";
      }
    } else {
      return "UNKNOWN";
    }
  }

  private static boolean useLowBandwidthCalling(@NonNull Context context) {
    switch (SignalStore.settings().getCallBandwidthMode()) {
      case HIGH_ON_WIFI:
        return !NetworkUtil.isConnectedWifi(context);
      case HIGH_ALWAYS:
        return false;
      default:
        return true;
    }
  }

  private static NetworkInfo getNetworkInfo(@NonNull Context context) {
    return ServiceUtil.getConnectivityManager(context).getActiveNetworkInfo();
  }
}
