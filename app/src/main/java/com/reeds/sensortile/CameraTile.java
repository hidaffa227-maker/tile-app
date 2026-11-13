package com.reeds.sensortile;
public class CameraTile extends BaseTile {
    String key() { return "camera"; }
    int normal() { return R.drawable.ic_camera; }
    int crossed() { return R.drawable.ic_camera_off; }
}
