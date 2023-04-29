Feature: Logout
Given that the user is logged in
And the user is on the dashboard page
And the user can see the logout button

When the user clicks on the logout button

Then the user session is terminated
And the user is redirected to the login screen

Given that the user is not logged in
And the user is on the login screen
And the user cannot see the logout button

When the user clicks on the logout button

Then an error message indicating the user is not logged in is prompted

Given that the user is logged in but the session has timed out
And the user is on the dashboard page
And the user can see the logout button

When the user clicks on the logout button

Then an error message indicating the session has timed out is prompted
And the user is redirected to the login screen
And the user session is terminated