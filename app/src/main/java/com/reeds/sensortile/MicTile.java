package com.reeds.sensortile;
public class MicTile extends BaseTile {
    String key() { return "mic"; }
    int normal() { return R.drawable.ic_mic; }
    int crossed() { return R.drawable.ic_mic_off; }
}
