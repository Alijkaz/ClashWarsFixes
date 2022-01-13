# ClashWarsFixes

Custom made plugin for ClashWars bedwars plugin

## What does it do?

As ClashWars is not configurable at all, I needed to customize some functionalities of the plugin and add some features that are lacking currently sadly.

So I made this plugin to fix the issues as much as It's possible to externally

## Features
- Boosting generators/forges to drop more resources
- Limiting generators to specific amount of dropped resources as currently ClashWars continuously drop more and more items
- Blocking placing a block on chests and enderchests

## Downloading
You can easily download the compiled version of the plugin from <a href="#">here</a>
or compile it on your own as it's opensource

## Config
```yaml
# Options that have something to do with generators / forges (or whatever you wanna call those)
generators:
  booster:
    # If the module is enabled
    enabled: true
    # How many resources should we drop in addition to the original generator drop
    # For example the default value 2, would drop 2 more iron ingots on 1 iron ingot drop from the generator
    drop-amount: 1
    # Delay on dropping multiplied amount from the original drop time (in seconds)
    # Leave it as it is if you don't know what this is and start tweaking multiply-by
    delay: 1
  limiter:
    # If the module is enabled
    enabled: true
    # Check radius (Amount would be in all directions XYZ)
    radius: 2
    # How many Items are allowed in the specified radius?
    limit: 20

  # Preventing some things from happening to make the gameplay better
  prevent:
    placing-block-on-chest: true
    placing-block-on-enderchest: true
```

## TO-DO
<input type="checkbox" disabled /> I dunno you tell me 😄

