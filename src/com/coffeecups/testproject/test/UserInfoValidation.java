package com.coffeecups.testproject.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.coffeecups.testproject.UserProfileActivity;

public class UserInfoValidation extends
		ActivityInstrumentationTestCase2<UserProfileActivity> {

	UserProfileActivity UserProfileActivity;
	TextView TVName;
	TextView TVSurname;
	TextView TVDob;
	ImageView photoIV;

	public UserInfoValidation() {
		super("com.coffeecups.testproject", UserProfileActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		UserProfileActivity = getActivity();

	}

	public void testElementPresense() {
		final View origin = UserProfileActivity.getWindow().getDecorView();
		TVName = (TextView) UserProfileActivity
				.findViewById(com.coffeecups.testproject.R.id.name);
		TVSurname = (TextView) UserProfileActivity
				.findViewById(com.coffeecups.testproject.R.id.surname);
		TVDob = (TextView) UserProfileActivity
				.findViewById(com.coffeecups.testproject.R.id.dob);
		photoIV = (ImageView) UserProfileActivity
				.findViewById(com.coffeecups.testproject.R.id.selection_profile_pic);
		assertNotNull(TVName);
		assertNotNull(TVSurname);
		assertNotNull(TVDob);
		assertNotNull(photoIV);

		ViewAsserts.assertOnScreen(origin, TVName);
		ViewAsserts.assertOnScreen(origin, TVSurname);
		ViewAsserts.assertOnScreen(origin, TVDob);
		ViewAsserts.assertOnScreen(origin, photoIV);
	}

	public void testIsProperlyvaluesDisplayed() {
		TVName = (TextView) UserProfileActivity
				.findViewById(com.coffeecups.testproject.R.id.name);
		TVSurname = (TextView) UserProfileActivity
				.findViewById(com.coffeecups.testproject.R.id.surname);
		TVDob = (TextView) UserProfileActivity
				.findViewById(com.coffeecups.testproject.R.id.dob);
		photoIV = (ImageView) UserProfileActivity
				.findViewById(com.coffeecups.testproject.R.id.selection_profile_pic);
		assertEquals(" Ivan", TVName.getText().toString());
		assertEquals(" Bordyug", TVSurname.getText().toString());
		assertEquals(" 10.09.1990", TVDob.getText().toString());
	}
}
