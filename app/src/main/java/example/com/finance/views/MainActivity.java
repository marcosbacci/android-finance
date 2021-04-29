package example.com.finance.views;

import android.app.DatePickerDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;

import example.com.finance.R;
import example.com.finance.SheetFragment;
import example.com.finance.controllers.BankController;
import example.com.finance.controllers.CreditCardController;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{

    private FloatingActionButton fabMain, fabExpenses, fabEarning;
    private boolean fabsHide = true;
    private DatePickerDialog.OnDateSetListener dateListner;
    private Toolbar mToolbar;
    private DrawerLayout dl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationMenu = findViewById(R.id.navigationMenu);
        navigationMenu.setNavigationItemSelectedListener(this);
        navigationMenu.bringToFront();

        mToolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(mToolbar);

        dl = findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,dl,mToolbar,R.string.open_drawer,R.string.close_drawer);

        dl.addDrawerListener(toggle);
        toggle.syncState();

        // Carregando fragmento principal
            getSupportFragmentManager().
                    beginTransaction().
                    add(R.id.fragmentContainer, new MainFragment()).
                    commit();

           // Carregar os FABS
                fabMain = findViewById(R.id.fabMain);
                fabMain.setOnClickListener(this);

                fabExpenses = findViewById(R.id.fabExpenses);
                fabExpenses.setOnClickListener(this);

                fabEarning = findViewById(R.id.fabEarnings);
                fabEarning.setOnClickListener(this);
    }

    void closeDrawer(){
        if(dl.isDrawerOpen(GravityCompat.START)){
            dl.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onClick(View view) {
        // A cada vez que eu clicar no FAB Principal chame o método show hide;
        if(view.getId() == R.id.fabMain){
            showHideFabs();
        }else if(view.getId() == R.id.fabExpenses){
            loadExpenseForm();
        }else if(view.getId() == R.id.fabEarnings){
            loadEarningsForm();
        }
    }


    private void loadEarningsForm(){
        changeActionBarTitle(R.string.actionbar_earnings_form, true);
        // Carregando fragmento formulário de despesas
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragmentContainer, new EarningsFormFragment()).
                commit();
        showHideFabs();
    }

    private void loadExpenseForm(){
        changeActionBarTitle(R.string.actionbar_expenses_form, true);
        // Carregando fragmento formulário de despesas
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragmentContainer, new ExpenseFormFragment()).
                commit();
        showHideFabs();
    }

    private void showHideFabs(){
        // Define a animação
            // 0 significa invisivel e 1 visivel
            // Indo de 0 a 1
        AlphaAnimation fadeAnimation = new AlphaAnimation(0,1);
        // Define a duração
        fadeAnimation.setDuration(1000);
        // Define o atraso
        fadeAnimation.setStartOffset(100);

        if(fabsHide){
            fabExpenses.setVisibility(View.VISIBLE);
            fabEarning.setVisibility(View.VISIBLE);
            fabExpenses.setAnimation(fadeAnimation);
            fabEarning.setAnimation(fadeAnimation);
        }else{
            fabExpenses.clearAnimation();
            fabEarning.clearAnimation();
            fabExpenses.setVisibility(View.GONE);
            fabEarning.setVisibility(View.GONE);
        }

        fabsHide = !fabsHide;
    }

    private void changeActionBarTitle(int stringID, boolean isNotHome){
        getSupportActionBar().setTitle(stringID);
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(isNotHome);
        getSupportActionBar().setDisplayShowHomeEnabled(isNotHome);*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Se o objeto nativo que foi clicado foi o botão de voltar(home)
        if(item.getItemId() == android.R.id.home){
            // Faça algo
            backToHome();
        }
        return super.onOptionsItemSelected(item);
    }

    private void backToHome(){
        changeActionBarTitle(R.string.app_name, false);
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragmentContainer, new MainFragment()).
                commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.drawerMenuMain:
                backToHome();
                break;
            case R.id.drawerMenuBank:

                // Existe uma conta bancária cadastrada
                if(BankController.get() != null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new BankInfoFragment()).commit();
                }
                // Se não existe
                else{
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new NoBankFragment()).commit();
                }
                break;
            case R.id.drawerMenuCredit:

                if(CreditCardController.get() != null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new InfoCreditCardFragment()).commit();
                }else{
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new NoCreditCardFragment()).commit();
                }
                break;
            case R.id.drawerMenuInvoices:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new BillListFragment()).commit();
                break;
            case R.id.drawerMenuInstallments:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new InstallmentsListFragment()).commit();
                break;
            case R.id.drawerMenuResumes:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new SheetFragment()).commit();
                break;
        }

        closeDrawer();
        return false;
    }
}
