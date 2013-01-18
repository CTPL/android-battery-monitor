package tk.newk.battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import static tk.newk.common.log.*;
import static tk.newk.battery.Common.*;
public class PowerConnectionReceiver extends BroadcastReceiver
{
  @Override
  public void onReceive(Context context, Intent intent)
  {
    logv(this, "PowerConnectionReceiver receive broadcast");
    if ( intent.getAction().equals(Intent.ACTION_POWER_CONNECTED))
    {
      logd(this, "Power is connected");
      power_supply_state = POWER_SUPPLY_STATE_CONNECTED;
      if (can_speak() && read_setting_boolean(PREF_KEY_TTS_POWER_STATE, true))
      {
        TTS_helper_class.say(
            "Power supply is connected, battery is charging", 0.5f);
      }
    }
    else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED))
    {
      logd(this, "Power is disconnected");
      power_supply_state = POWER_SUPPLY_STATE_DISCONNECTED;
      if (can_speak() && read_setting_boolean(PREF_KEY_TTS_POWER_STATE, true))
      {
        TTS_helper_class.say("Power supply is disconnected", 0.5f);
      }
    }
  }
}

