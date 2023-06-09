/*
package ba.etf.rma23.projekat

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.rma_spirala.R
import org.hamcrest.CoreMatchers
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class OwnEspressoTests {
    @Test
    fun FragmentNavigationTest() {
        //Scenario: Korisnik otvara aplikaciju i klika na trecu igricu u listi zatim se vraca na prvi fragment

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        var clickedItem = GameData.getAll().get(2)
        onView(withId(R.id.game_list)).perform(
            RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                CoreMatchers.allOf(
                    hasDescendant(withText(clickedItem.title)),
                    hasDescendant(withText(clickedItem.releaseDate)),
                    hasDescendant(withText(clickedItem.rating.toString()))
                ), ViewActions.click()
            ))

        //da li je gameDetailsFragment vidljiv nakon klika na igricu (platform_textview je id teksta koji ispisuje platformu na gameDetailsFragment)
        onView(withId(R.id.platform_textview)).check(matches(isDisplayed()))

        //da li je ispravna igrica prikazana
        onView(withText(clickedItem.releaseDate)).check(matches(isCompletelyDisplayed()))

        //da li je homeFragment vidljiv nakon klika na home ikonu na bottomNavigationBar (search_query_edittetxt je id search bar-a na homeFragment)
        onView(withContentDescription("Home")).perform(ViewActions.click())
        onView(withId(R.id.search_query_edittext)).check(matches(isDisplayed()))
    }

    @Test
    fun PreviousGameTest() {
        //Scenario: Korisnik otvara aplikaciju, klika na razlicite igrice i vraca se na pocetni fragment par puta zatim klika na details ikonu na bottomNavigationBar
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        //bottomNavigationBar ne treba biti pristupacan prije nego sto se klikne na igricu
        onView(withContentDescription("Home")).check(matches(isNotEnabled()))
        var clickedItem: Game = GameData.getAll().get(9)
        for(i in 0..2) {
            clickedItem = GameData.getAll().get(i)
            onView(withId(R.id.game_list)).perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                    CoreMatchers.allOf(
                        hasDescendant(withText(clickedItem.title)),
                        hasDescendant(withText(clickedItem.releaseDate)),
                        hasDescendant(withText(clickedItem.rating.toString()))
                    ), ViewActions.click()
                ))
            onView(withContentDescription("Home")).perform(ViewActions.click())
        }

        //nakon klika na Details ikonu trebaju se otvoriti detalji o zadnjoj igrici na koju je korisnik kliknuo
        onView(withContentDescription("Home")).check(matches(isEnabled()))
        onView(withContentDescription("Details")).perform(ViewActions.click())
        onView(withText(clickedItem.releaseDate)).check(matches(isCompletelyDisplayed()))
    }
}*/
