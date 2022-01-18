# DefaultAppComponents

This library is a collection of some components which I use in nearly every of my projects.

## <a href="https://github.com/SoWieMarkus/DefaultAppComponents/tree/master/defaultAppComponents/src/main/java/markus/wieland/defaultappelements/api">API</a>

In many android apps I have to implement an API. Because I like to write my own libraries I don't use libraries like <a href="https://square.github.io/retrofit/">Retrofit</a>.
But if you want to use my API library you may need the <a href="https://github.com/google/gson">GSON library</a>.

### Example

#### 1. Create models

Create a model class for every "class" you will get from your API. 

Example from my <a href="https://github.com/SoWieMarkus/TrackmaniaCOTDApp">Trackmania Cup ot the Day App</a>:

http://sowiemarkus.com:8080/cotd/2021/1/29

```javascript
{
  id: 192,
  name: "Cup of the Day 2021-01-29",
  year: 2021,
  month: 1,
  day: 29,
  startTime: 1611944190,
  endTime: 1611951390,
  leaderBoardId: 772,
  players: 1347,
  playerResult: [
    {
      id: "cotd_player_result_2021_1_29_0",
      accountId: "4630e91c-85f8-4d8a-9465-f87acc76ae59",
      displayName: "rustyh1",
      zone: "{"name":"Upper Austria","flag":"Upper_Austria","parent":{"name":"Austria","flag":"AUT","parent":{"name":"Europe","flag":"europe","parent":     {"name":"World","flag":"WOR"}}}}",
      position: 0
    }, ...
  ]
}
```

In this example you need two model classes. Name the variable exactly like they will be returned from the API. If you don't want to use the variable name from the API (because it doesn't fit the style guide or something like that) you can use the GSON library and annotate the corresponding field with @SerializedName("<api variable name>")

```java

public class CupResults {
    private long id;
    private String name;
    private int year;
    private int month;
    private int day;
    private long startTime;
    private long endTime;
    private long leaderBoardId;
    private long playsers;
    
    // api: playerResult -> its a list, I want to name it playerResults
    @SerializedName("playerResult")
    // PlayerResult is a new model class
    private List<PlayerResult> playerResults;
  
    public CupResults(){}
  
    // Add getter and setter
}
```

```java

public class PlayerResult {
    private String id;
    private String accountId;
    private String displayName;
    private String zone;
    private long position; 
  
    public PlayerResult(){}
  
    // Add getter and setter
}
```

#### 2. Create API class and execute GET - Request (other requests will follow as soon as I need them)

```java
public class MyAPI extends API {

    private static final String BASE_URL = "http://my_api_url.com";

    public MyAPI(Activity context) {
        super(context);
    }

    public void getCupResult(APIResult<CupResults> result, int year, int month, int day) {
        String url = BASE_URL + year + "/" + month + "/" + day;
        // Generic
        // use your "main" model class (in this example CupOfTheDayResult)
        GetRequest<CupResults> getRequest = new GetRequest<>(CupResults.class, url, new RequestResultListener<COTD>() {
            @Override
            public void onLoad(CupResults response) {
                // Notify client about result
                notifyClient(response, result);
            }

            @Override
            public void onError(Exception e) {
                // Handle error (I return null so I know something went wrong)
                // You could also throw exception
                notifyClient(null, result);
            }
        });
        getRequest.execute();
    }

```
The GetRequest is an AsyncTask. It will get the API result, parse the json string with the GSON library into an instance of our model class and will notify the API class when its finished. The API class will notify the activity via the notifyClient function.

#### 3. Class API
  
```java
  
public class MainActivity extends AppCompatActivity implements ApiResult<CupResults>{
  
    private MyAPI myAPI;
  
    ...
  
    private void init(){
        myAPI = new MyAPI(this);
    }
  
    private void search(int year, int month, int day){
        myAPI.getCupResults(this, year, month, day);
    }
  
    public void onLoad(CupResults result){
        // Handle result
    }
  
  
}
  
```

If you want to get multiple endpoints of an api in the same activity you can also use java lambda terms.
  
```java
...
myAPI.loadModelClass1(this::onLoad);
myAPI.loadModelClass2(this::onLoad);
...
  
public void onLoad(ModelClass1 result){}
  
public void onLoad(ModelClass2 result){}
  
```

## <a href="https://github.com/SoWieMarkus/DefaultAppComponents/tree/master/defaultAppComponents/src/main/java/markus/wieland/defaultappelements/textinputvalidator">TextInputValidator</a>

Very often I have to verify if a String from an EditText does fit some requirements. For this I created the TextInputValidator library.
  
### Example
  
#### 1. Initialize the TextInputValidator
  
First of all we have to initialize the TextInputValidator. Therefore we create an instance of the class and add "arguments" to it. There are already some given arguments <a href="https://github.com/SoWieMarkus/DefaultAppComponents/tree/master/defaultAppComponents/src/main/java/markus/wieland/defaultappelements/textinputvalidator/arguments">here</a>
  
```java

// first create instance of TextInputValidator
TextInputValidator validator = new TextInputValidator();
  
// add the argument max length
// will check if the string size is smaller or equal to 20
validator.add(new MaxLengthValidatorArgument(20, "ERROR_MESSAGE");
...
  
 
```
  
You can also create your own arguments. 
  
```java
  
// for example "contains the substring 'aaa'"
public class ContainsAAAArgument extends TextInputValidatorArgument {

    public ContainsAAAArgument() {
        super("Does not contain 'AAA'");
    }


    @Override
    public boolean validate(String string) {
        return string.contains("AAA");
    }
}

```
  
2. Check if a string matches all requirements
  
```java
  
ValidatorResult result = validator.validate(ýourString);
if (result.isValid()) {
    // does match all requirements
} else {
    // Soemthing went wrong
    throw new Exception(result.getErrorMessage());
}
  
```
  
  ```java
  
validator.add(new ContainsAAAArgument());
ValidatorResult result = validator.validate("bbbAAA");
ValidatorResult result2 = validator.validate("bbbAA");
  
System.out.println(result.isValid()); // -> true
System.out.println(result2.isValid()); // -> false
System.out.println(result.getErrorMessage()); // -> null
System.out.println(result2.getErrorMessage()); // -> "Does not contain 'AAA'"
  
validator.add(new MaxLengthValidatorArgument(3, "Too long");
result = validator.validate("bbbAAA");
result2 = validator.validate("bbbAAA");
  
System.out.println(result.isValid()); // -> false
System.out.println(result2.isValid()); // -> false
System.out.println(result.getErrorMessage()); // -> "Too long"
System.out.println(result2.getErrorMessage()); // -> "Does not contain 'AAA'", because first argument is ContainsAAAArgument
```
  
## <a href="https://github.com/SoWieMarkus/DefaultAppComponents/tree/master/defaultAppComponents/src/main/java/markus/wieland/defaultappelements/uielements/activities">Structured activity</a>
  
I like my android activities structred. That's why I create the <a href="https://github.com/SoWieMarkus/DefaultAppComponents/blob/master/defaultAppComponents/src/main/java/markus/wieland/defaultappelements/uielements/activities/DefaultActivity.java">DefaultActivity</a> class. 
  
```java
public class MyActivity extends DefaultActivity {
  
  private View myView;
  
  // create constructor with layout res
  public MyActivity(){
      super(R.layout.my_activity);
  }
  
  // first call in super on create
  public void bindViews() {
      myView = findViewById(R.id.my_view);
  }

  //called after bindViews
  public void initializeViews() {
      myView.setOnClickListener(...);
  }

  //called after initializeViews
  public void execute() {
      // e.g. execute api call
  }
  
}
```
  
The same principal is used for Fragments inside the DefaultFragment. (I know Android Studio wants you to create with static method new instance). Interesting is also, that I implemented the "findViewById" function for Fragments. For that I store the view after it got inflated.
  
```java
public class MyFragment extends DefaultFragment {

    public MyFragemnt(){
        super(R.layout.my_layout);
    }
    
    //called first, bind your views
    public void bindViewss(){
        // you can use findViewById here!
        ...
        view = findViewById(R.id.my_id);
  
      
    }
  
    // set up your views
    public void initializeViews(){
        // load images
        // set text etc.
    }
  
    public void execute(){
    
    }
  
}
```
  
Based on this DefaultActivity I have created the <a href="https://github.com/SoWieMarkus/DefaultAppComponents/blob/master/defaultAppComponents/src/main/java/markus/wieland/defaultappelements/uielements/activities/CreateItemActivity.java">CreateItemActivity</a>, because very often I need a new activity where I can add or edit a item. It will automatically create menu with save and discard in the ActionBar.
  
```java
  
public class MyItem implements Serializable {}

// Start the CreateItemActivity from parent activity
// edit
startActivityForResult(new Intent(this, MyItemActivity.class).putExtra(CreateItemActivity.OBJECT_TO_EDIT, item), your_code);
// create
startActivityForResult(new Intent(this, MyItemActivity.class), your_code);

public abstract class MyItemActivity extends CreateItemActivity<MyItem> {

    public MyItemActivity() {
        super(R.layout.my_item_layout);
    }

    // first call
    public void bindViews(){
        // bind your views
    }
  
    // called if we passed an item via an intent to this activity
    public void initializeViewsEditMode() {
        // set up edit texts etc. with attributes of item
    }

    // called if no item was passed via intent so we want to create a new item
    public void initializeViewsAddMode() {
        // set up blank
    }

    // After we call commitItem() this method will check if our item is valid
    // if this method returns true the activity will finish and the result will be send as result
    protected boolean validateItem() {
        // check the attributes
        return true;
    }

    // User pressed save
    protected abstract void onCommitItem() {
        // set / update the attributes of our item
        commitItem();
    }
}
  
// get result in parent activity
onActivityResult(...){
    MyItem item = intent.getSerializableExtra(CreateItemActivity.RESULT);
    
}
```
  
## <a href="https://github.com/SoWieMarkus/DefaultAppComponents/tree/master/defaultAppComponents/src/main/java/markus/wieland/defaultappelements/uielements/adapter">Smart Adapter</a>
  
I very often use RecyclerViews and have to develop a adapter for the RecyclerView. So I developed a easier to use version. Therefore you need a class which you want to display in with the adapter. In the example it would be the class "Item"
  
### Example
  
```java
public class SmartAdapter extends DefaultAdapter<Item, SmartAdapter.SmartViewHolder> {

    // There are already some InteractionListener that can be set here
    public SmartAdapter(OnItemInteractListener<Type> onItemInteractListener) {
        super(onItemInteractListener);
    }

    @NonNull
    @Override
    public LevelBuilderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LevelBuilderItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_level_builder, parent, false));
    }
  
    // Override the Getter method so you can get the correct InteractionListener
    @Override
    public OnItemClickListener<Type> getOnItemInteractListener() {
        return (OnItemClickListener<Type>) super.getOnItemInteractListener();
    }

    public class SmartViewHolder extends DefaultViewHolder<Item> {

        private TextView textView;

        public SmartViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bindViews() {
            // Again the store is saved in the super class
            // Based on this there is again a find view by id method that you can call here
            // you dont have to call itemView.findViewByIf(...)
            textView = findViewById(R.id.text_view);
        }

        @Override
        public void bindItemToViewHolder(Item item) {
            // update the view
            textViews.setText(item.toString());
        }

    }
}
```

Often the list changes dynamically because a new item got inserted. In that case you can use my _QueryableAdapter_. This will automatically animate changes inside the RecyclerView. But the "Item" class needs to implement <a href="https://github.com/SoWieMarkus/DefaultAppComponents/blob/master/defaultAppComponents/src/main/java/markus/wieland/defaultappelements/uielements/adapter/QueryableEntity.java">QueryableEntity</a>. This will give the Entity an id and a method to check with a possible query if you search for a item inside a list. The Id is needed to identify a element inside a list (is it new? is it missing? did it change position?). The generic class T is the class of the id. Some ids might be a String, some might be a Long. Thats why it's designed as a Generic.

```java
class Item implements QueryableEntity<Long> {
    
  private Long id;
  private String text;
  
  public Item(Long id, String text) {
    this.id = id;
    this.text = text;
  }
  
  @Override
  public Long getId(){
    return id;
  }
  
  @Override
  public String getStringToApplyQuery() {
    return text;
  }
}

```
    
The adapter then needs to implement <a href="https://github.com/SoWieMarkus/DefaultAppComponents/blob/master/defaultAppComponents/src/main/java/markus/wieland/defaultappelements/uielements/adapter/QueryableAdapter.java">QueryableAdapter</a>. The three generics are: class of the id of the item, the item class, the view holder class which extends DefaultViewHolder<Item>.
  
## <a href="https://github.com/SoWieMarkus/DefaultAppComponents/tree/master/defaultAppComponents/src/main/java/markus/wieland/defaultappelements/uielements/viewpager"> ViewPageAdapter</a>

A custom ViewPageAdapter is something I used very often in the past. So I made a easy to use ViewPagerAdapter (even if the ViewPager is deprecated). The adapter uses the _ViewPageAdapterItem_. It consist of a fragment and a title. The fragment will be shown when the page is selected. The title is the String that is displayed in the TabLayout.

<img src="https://androidwave.com/wp-content/uploads/2019/08/android-viewpager-with-tabs.png" alt="ViewPager" />
  
```java

// from the example above it would look like this:
List<ViewPageAdaperItem> items = new ArrayList<>();
items.add(new ViewPageAdpaterItem("Speed Dial", fragment1));
items.add(new ViewPageAdpaterItem("Recent", fragment2));
items.add(new ViewPageAdpaterItem("Contacts", fragment3)); 
 
ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager(), items);
 
```
  
  








