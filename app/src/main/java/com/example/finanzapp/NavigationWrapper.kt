import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.finanzapp.CalendarScreen
import com.example.finanzapp.EntryScreen
import com.example.finanzapp.GraphScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Menu) {
        composable<Menu> {
            PrincipalScreen(
                { navController.navigate(Income) },
                { navController.navigate(Graphics) },
                { navController.navigate(Timetable) },
            )
        }
        composable<Income> {backStackEntry ->
            EntryScreen( navigateBack = {navController.navigateUp()})
        }
        composable<Graphics> {backStackEntry ->
            GraphScreen( navigateBack = {navController.navigateUp()})
        }
        composable<Timetable> {backStackEntry ->
            CalendarScreen(navigateBack = {navController.navigateUp()})
        }
    }
}