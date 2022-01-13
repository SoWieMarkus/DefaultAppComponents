# DefaultAppComponents

This library is a collection of some components which I use in nearly every of my projects.

## <a href="https://github.com/SoWieMarkus/DefaultAppComponents/tree/master/defaultAppComponents/src/main/java/markus/wieland/defaultappelements/api">API</a>

In many android apps I have to implement an API. Because I like to write my own libraries I don't use libraries like <a href="https://square.github.io/retrofit/">Retrofit</a>.
But if you want to use my API library you may need the <a href="https://github.com/google/gson">GSON library</a>.

### Example

#### 1. Create models

Create a model class for every "class" you will get from your API. 

Example from my <a href="https://github.com/SoWieMarkus/TrackmaniaCOTDApi">Trackmania API</a>:

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











