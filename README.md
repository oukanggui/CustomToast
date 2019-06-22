# CustomToast
A simple custom Toast Helper
# How to use ?
 <b>ToastHelper toastHelper = new ToastHelper(context);<b><br>
 <b>//toastHelper.setView(R.layout.toast_custom)<b><br>
 //If you don't want to use the default layout(R.layout.toast_custom) for the toast,<br>//you need to call setView() mothed to set the custom view for the Toast before calling setText() and setImage motheds.<br>
 //Also,the custom layout must contain the id @+id/toast_icon for ImageView and the id toast_message for TextView<br>
 <b>toastHelper.setText("I am baymax from custom toast");<b><br>
 <b>toastHelper.show();<b><br>
