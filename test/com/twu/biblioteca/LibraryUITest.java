package com.twu.biblioteca;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by fergyo on 16/12/2016.
 */
@RunWith(HierarchicalContextRunner.class)
public class LibraryUITest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final InputStreamBuilder stubbedInput = new InputStreamBuilder();
    private final Library mockedLibrary = mock(Library.class);

    private final String LIST_BOOKS = "1";
    private final String LIST_MOVIES = "2";
    private final String CHECKOUT_ITEM = "3";
    private final String RETURN_ITEM = "4";
    private final String LOGIN = "5";
    private final String DISPLAY_USER = "6";
    private final String QUIT = "Q";
    private final String ITEM_NAME = "Awesome book";
    private final String USER_ID = "123-4567";
    private final String PASSWORD = "passw0rd";
    private final String MENU = "What action would you like to perform today?\n" +
            "1. List books\n" +
            "2. List movies\n" +
            "3. Checkout item\n" +
            "4. Return item\n" +
            "5. Login\n" +
            "6. Display User Details\n" +
            "Q. Quit program\n" +
            "Type the number of the desired menu item and press enter";


    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setIn(null);
        System.setErr(null);
    }

    public class StartingLibrary {

        @Test
        public void greetedWithWelcomeMessage() {
            System.setIn(stubbedInput.toReturn(QUIT).atSomePoint());

            LibraryUI libraryUI = new LibraryUI(mockedLibrary);
            libraryUI.start();

            assertThat(outContent.toString(), containsString("Hello and welcome to the Biblioteca"));
        }

        @Test
        public void displaysMainMenu() {
            System.setIn(stubbedInput.toReturn(QUIT).atSomePoint());

            LibraryUI libraryUI = new LibraryUI(mockedLibrary);
            libraryUI.start();

            assertThat(outContent.toString(), containsString(MENU));
        }

        @Test
        public void libraryAppQuitsWhenUserChoosesQFromMenu() {
            System.setIn(stubbedInput.toReturn(QUIT).atSomePoint());

            String exitMessage = "Thank you for visiting biblioteca";

            LibraryUI libraryUI = new LibraryUI(mockedLibrary);
            libraryUI.start();

            assertThat(outContent.toString(), containsString(exitMessage));
        }

        @Test
        public void failureMessageOnIncorrectMenuSelection() throws Exception {
            System.setIn(stubbedInput.toReturn("err").then(QUIT).atSomePoint());
            String menuError = "Sorry that is not a valid option";

            LibraryUI libraryUI = new LibraryUI(mockedLibrary);
            libraryUI.start();

            assertThat(outContent.toString(), containsString(menuError));
        }
    }

    public class ListItems {
        @Test
        public void menuItem1ListsBooks() {
            System.setIn(stubbedInput.toReturn(LIST_BOOKS).then(QUIT).atSomePoint());

            LibraryUI libraryUI = new LibraryUI(mockedLibrary);
            libraryUI.start();

            verify(mockedLibrary).listAvailableItemsOfType(Book.class);
        }

        @Test
        public void menuItem2ListsMovies() {
            System.setIn(stubbedInput.toReturn(LIST_MOVIES).then(QUIT).atSomePoint());

            LibraryUI libraryUI = new LibraryUI(mockedLibrary);
            libraryUI.start();

            verify(mockedLibrary).listAvailableItemsOfType(Movie.class);
        }
    }

    public class UserLogin {
        @Before
        public void LoggingIn() {
            System.setIn(stubbedInput.toReturn(LOGIN).then(USER_ID).then(PASSWORD).then(QUIT).atSomePoint());
        }

        @Test
        public void userAskedToProvideIDAndPassword() {
            String enterLibraryNumber = "Please enter your library number";
            String enterPassword = "Please enter your password";

            LibraryUI libraryUI = new LibraryUI(mockedLibrary);
            libraryUI.start();

            assertThat(outContent.toString(), allOf(containsString(enterLibraryNumber), containsString(enterPassword)));
        }

        @Test
        public void failureMessageIfCustomerIDOrPasswordIsIncorrect() {
            when(mockedLibrary.checkPassword(USER_ID,PASSWORD)).thenReturn(false);

            String failureMessage = "Password or user ID is incorrect";

            LibraryUI libraryUI = new LibraryUI(mockedLibrary);
            libraryUI.start();

            assertThat(outContent.toString(), containsString(failureMessage));
        }


        @Test
        public void SuccessMessageIfCorrectCustomerIDAndPassword() {
            when(mockedLibrary.checkPassword(USER_ID, PASSWORD)).thenReturn(true);
            when(mockedLibrary.getActiveUserName()).thenReturn("Bob");

            String failureMessage = "Bob is now logged in";

            LibraryUI libraryUI = new LibraryUI(mockedLibrary);
            libraryUI.start();

            assertThat(outContent.toString(), containsString(failureMessage));
        }
    }

    public class WhenUserLoggedIn {
        @Before
        public void mockUserLoggedIn() {
            when(mockedLibrary.getActiveUserName()).thenReturn("Bob");
        }

        public class checkingOutItems {

            @Test
            public void choosing3FromMenuGivesOptionToCheckOutItem() {
                System.setIn(stubbedInput.toReturn(CHECKOUT_ITEM).then(ITEM_NAME).then(QUIT).atSomePoint());

                LibraryUI libraryUI = new LibraryUI(mockedLibrary);
                libraryUI.start();
                String checkoutMsg = "Please enter the title of the item you would like to checkout";

                assertThat(outContent.toString(), containsString(checkoutMsg));
                verify(mockedLibrary).checkoutItem(ITEM_NAME);
            }

            @Test
            public void failureMessageIfItemIsNotAvailable() {
                System.setIn(stubbedInput.toReturn(CHECKOUT_ITEM).then(ITEM_NAME).then(QUIT).atSomePoint());
                when(mockedLibrary.checkoutItem(ITEM_NAME)).thenReturn(false);

                LibraryUI libraryUI = new LibraryUI(mockedLibrary);
                libraryUI.start();
                String checkoutMsg = "That selection is not available";

                assertThat(outContent.toString(), containsString(checkoutMsg));
            }

            @Test
            public void messageOnSuccess() {
                System.setIn(stubbedInput.toReturn(CHECKOUT_ITEM).then(ITEM_NAME).then(QUIT).atSomePoint());
                when(mockedLibrary.checkoutItem(ITEM_NAME)).thenReturn(true);
                String checkoutMessage = "Thank you! Enjoy your selection";

                LibraryUI libraryUI = new LibraryUI(mockedLibrary);
                libraryUI.start();


                assertThat(outContent.toString(), containsString(checkoutMessage));
            }
        }

        public class returningItems {
            @Before
            public void setsInputStream(){
                System.setIn(stubbedInput.toReturn(RETURN_ITEM).then(ITEM_NAME).then(QUIT).atSomePoint());
            }

            @Test
            public void accessedByMenuItem4() {
                LibraryUI libraryUI = new LibraryUI(mockedLibrary);
                libraryUI.start();
                String returnMsg = "Please enter the title of the item you would like to return";

                assertThat(outContent.toString(), containsString(returnMsg));
                verify(mockedLibrary).returnItem(ITEM_NAME);
            }

            @Test
            public void messageOnSuccess() {
                when(mockedLibrary.returnItem(ITEM_NAME)).thenReturn(true);

                LibraryUI libraryUI = new LibraryUI(mockedLibrary);
                libraryUI.start();
                String returnMsg = "Thank you for returning the item";

                assertThat(outContent.toString(), containsString(returnMsg));
            }

            @Test
            public void failureMessageIfItemIsNotReturnable() {
                when(mockedLibrary.returnItem(ITEM_NAME)).thenReturn(false);
                String returnMsg = "That is not a valid item to return";

                LibraryUI libraryUI = new LibraryUI(mockedLibrary);
                libraryUI.start();

                assertThat(outContent.toString(), containsString(returnMsg));
            }
        }

        @Test
        public void choosing6FromMenuReturnsUserDetails() {
            System.setIn(stubbedInput.toReturn(DISPLAY_USER).then(QUIT).atSomePoint());
            when(mockedLibrary.activeUserDetails()).thenReturn("Some string of user details");

            LibraryUI libraryUI = new LibraryUI(mockedLibrary);
            libraryUI.start();

            assertThat(outContent.toString(), containsString("Some string of user details"));
        }

    }

    public class WhenNoUserLoggedIn {

        @Before
        public void mockNoUserLoggedIn() {
            when(mockedLibrary.getActiveUserName()).thenReturn("No User");
        }

        @Test
        public void itemsCanNotBeCheckedOut() {
            System.setIn(stubbedInput.toReturn(CHECKOUT_ITEM).then(ITEM_NAME).then(QUIT).atSomePoint());

            String checkoutMessage = "Thank you! Enjoy your selection";
            String noUserMessage = "A user must be logged in to checkout an item";

            LibraryUI libraryUI = new LibraryUI(mockedLibrary);
            libraryUI.start();

            assertThat(outContent.toString(), allOf(
                    not(containsString(checkoutMessage)),
                    containsString(noUserMessage)
            ));
        }

        @Test
        public void itemsCanNotBeReturned() {
            System.setIn(stubbedInput.toReturn(RETURN_ITEM).then(ITEM_NAME).then(QUIT).atSomePoint());

            String returnMessage = "Thank you for returning the item";
            String noUserMessage = "A user must be logged in to return an item";

            LibraryUI libraryUI = new LibraryUI(mockedLibrary);
            libraryUI.start();

            assertThat(outContent.toString(), allOf(
                    not(containsString(returnMessage)),
                    containsString(noUserMessage)
            ));
        }

        @Test
        public void choosing6FromMenuReturnsNoUserMessage() {
            System.setIn(stubbedInput.toReturn(DISPLAY_USER).then(QUIT).atSomePoint());
            when(mockedLibrary.activeUserDetails()).thenReturn("No User");

            LibraryUI libraryUI = new LibraryUI(mockedLibrary);
            libraryUI.start();

            assertThat(outContent.toString(), containsString("No User"));
        }
    }


}