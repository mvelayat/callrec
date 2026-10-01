package com.example.callrec

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

class CallAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // عمداً خالی است؛ فقط فعال بودن این سرویس مهم است
    }

    override fun onInterrupt() {
        // عمداً خالی است
    }
}
