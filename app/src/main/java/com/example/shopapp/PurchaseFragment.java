package com.example.shopapp;



import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.shopapp.Adapter.CartAdapter;
import com.example.shopapp.Model.itemsModel;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import java.util.ArrayList;
import java.util.List;

public class PurchaseFragment extends Fragment implements PaymentResultListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView purchaseRecyclerView;
    private CartAdapter cartAdapter;
    private Context context;

    public PurchaseFragment() {
        // Required empty public constructor
    }

    public static PurchaseFragment newInstance(String param1, String param2) {
        PurchaseFragment fragment = new PurchaseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_purchase, container, false);


        purchaseRecyclerView = rootView.findViewById(R.id.rv_recorderlist);
        purchaseRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Load data from SharedPreferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("CartDetails", Context.MODE_PRIVATE);
        String cartItemsJson = sharedPreferences.getString("cartItems", "[]");

        List<itemsModel> cartItems = new ArrayList<>();
        try {
            JSONArray cartArray = new JSONArray(cartItemsJson);
            for (int i = 0; i < cartArray.length(); i++) {
                JSONObject itemObject = cartArray.getJSONObject(i);
                String name = itemObject.optString("name");
                String type = itemObject.optString("type");
                String price = itemObject.optString("price");
                int image = itemObject.optInt("image");

                // Add item to the list
                cartItems.add(new itemsModel(name, type, price, image));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Check if the list is populated
        if (cartItems.isEmpty()) {
            System.out.println("Cart items list is empty.");
        } else {
            System.out.println("Cart items loaded: " + cartItems.size());
        }


        // Set up the adapter


        // Update totals from SharedPreferences
        String totalProductPrice = sharedPreferences.getString("totalProductPrice", "0");
        String shippingCharges = sharedPreferences.getString("shippingCharges", "0");
        String orderDiscount = sharedPreferences.getString("orderDiscount", "0");
        String finalPrice = sharedPreferences.getString("finalPrice", "0");

        // Bind totals to TextViews
        TextView finalPriceTextView = rootView.findViewById(R.id.final_price);
        TextView orderDiscountTextView = rootView.findViewById(R.id.order_discount);
        TextView shippingChargesTextView = rootView.findViewById(R.id.shipping_charges);
        TextView totalProductPriceTextView = rootView.findViewById(R.id.total_product_price);

        finalPriceTextView.setText(finalPrice);
        orderDiscountTextView.setText(orderDiscount);
        shippingChargesTextView.setText(shippingCharges);
        totalProductPriceTextView.setText(totalProductPrice);
        cartAdapter = new CartAdapter(cartItems, getContext(),
                totalProductPriceTextView,
                shippingChargesTextView,
                orderDiscountTextView,
                finalPriceTextView);
        purchaseRecyclerView.setAdapter(cartAdapter);
        // Initialize the Razorpay payment button
        RadioGroup radioGroup = rootView.findViewById(R.id.radiogroup_paymentmethod);
        rootView.findViewById(R.id.btn_placeorder).setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();

            if (selectedId == R.id.radiobutton_onlinepayment) {
                startPayment(); // Navigate to Razorpay payment page
            } else if (selectedId == R.id.radiobutton_cod) {
                Fragment fragment_cash_on_delivery = new Cash_on_delivery();
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.linearLayout, fragment_cash_on_delivery)
                        .addToBackStack(null)
                        .commit();
            } else {
                Log.e("Payment", "No payment method selected.");
            }
        });


        return rootView;
    }

    private void startPayment() {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_KRCDODFgP0fIyM"); // Replace with your Razorpay Key

        JSONObject paymentDetails = new JSONObject();
        try {
            paymentDetails.put("name", "Shop App");
            paymentDetails.put("description", "Test Payment");
            paymentDetails.put("currency", "INR");

            // Fetch finalPrice from SharedPreferences
            SharedPreferences sharedPreferences = requireContext().getSharedPreferences("CartDetails", Context.MODE_PRIVATE);
            String finalPriceString = sharedPreferences.getString("finalPrice", "0");

            // Remove the currency symbol (Rs) if present
            finalPriceString = finalPriceString.replaceAll("[^0-9]", ""); // This removes any non-numeric characters

            // Parse finalPrice to integer
            int finalPrice = 0;
            try {
                finalPrice = Integer.parseInt(finalPriceString);
            } catch (NumberFormatException e) {
                Log.e("Razorpay", "Error parsing finalPrice: " + finalPriceString, e);
            }

            // Validate the final price
            if (finalPrice <= 0) {
                Log.e("Razorpay", "Invalid final price: " + finalPrice);
                return; // Abort the payment process
            }

            // Convert to paise (Razorpay requires the amount in paise)
            int amountInPaise = finalPrice * 100;
            paymentDetails.put("amount", amountInPaise); // Razorpay requires the "amount" key

            // Prefill details
            paymentDetails.put("prefill", new JSONObject()
                    .put("email", "test@example.com")
                    .put("contact", "1234567890"));

            // Open Razorpay checkout
            if (isAdded() && getActivity() != null && !getActivity().isFinishing()) {
                Log.d("Razorpay", "Opening Razorpay with amount: " + amountInPaise);
                checkout.open(getActivity(), paymentDetails);
            } else {
                Log.e("Razorpay", "Fragment or Activity is not valid.");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onPaymentSuccess(String paymentId) {
        // Handle payment success
        // Example: Toast.makeText(getContext(), "Payment Successful! Payment ID: " + paymentId, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPaymentError(int code, String response) {
        // Handle payment failure
        // Example: Toast.makeText(getContext(), "Payment Failed: " + response, Toast.LENGTH_LONG).show();
    }

    private void navigateToNextPage() {




    }


}
