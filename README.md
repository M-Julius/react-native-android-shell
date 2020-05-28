# react-native-android-shell
This module for execute shell android, use command and execute shell

## Screenshot
![Alt text](img/ls.png?raw=true "ls command")
![Alt text](img/id.png?raw=true "id command")
![Alt text](img/uname.png?raw=true "uname command")


## Getting started

`$ npm install react-native-android-shell --save`

## Usage
```javascript
import AndroidShell from 'react-native-android-shell';

// TODO: What to do with the module?
AndroidShell.executeCommand('your Command', (result) => {
  console.log(result)
});
```
## Example
```javascript
import React, { Component } from 'react';
import { View, Text } from 'react-native';
import AndroidShell from 'react-native-android-shell'

class demo extends Component {
    constructor(props) {
        super(props);
        this.state = {
            result: ''
        };
    }

    componentDidMount() {
        AndroidShell.executeCommand("ls", (result) => {
            this.setState({ result: 'yeu : ' + result });
            console.log('Result :', result);
        });
    }

    render() {
        return (
            <View style={{alignItems: 'center', flex: 1 }}>
                <Text>{this.state.result}</Text>
            </View>
        );
    }
}

export default demo;
```
