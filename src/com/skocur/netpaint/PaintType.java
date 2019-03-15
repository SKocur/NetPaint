package com.skocur.netpaint;

/**
 * Simple ENUM that holds two values:
 * -CLIENT
 * -SERVER
 *
 * Each of them is used on program startup in order to choose how
 * NetPaint should behave, whether like Client or Server.
 */
public enum PaintType {
    CLIENT,
    SERVER
}
