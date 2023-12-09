
```
wordle
├─ .gitignore
├─ .metals
│  ├─ metals.lock.db
│  ├─ metals.log
│  └─ metals.mv.db
├─ .travis.yml
├─ .vscode
│  └─ settings.json
├─ README.md
├─ build.sbt
├─ project
│  ├─ Dependencies.scala
│  └─ build.properties
└─ src
   ├─ main
   │  ├─ resources
   │  │  ├─ application.conf
   │  │  ├─ logback.xml
   │  │  └─ wordlist.txt
   │  └─ scala
   │     └─ com
   │        ├─ WordleApp.scala
   │        ├─ actors
   │        │  ├─ GameManager.scala
   │        │  ├─ PlayerActor.scala
   │        │  └─ WordActor.scala
   │        ├─ cluster
   │        │  ├─ ClusterListener.scala
   │        │  └─ NodeManager.scala
   │        ├─ messages
   │        │  └─ GameMessages.scala
   │        ├─ ui
   │        │  ├─ GameUI.scala
   │        │  └─ PlayerUI.scala
   │        └─ util
   │           ├─ ConfigUtils.scala
   │           └─ WordDictionary.scala
   └─ test
      └─ scala
         └─ com
            └─ wordledist
               └─ WordleGameTest.scala

```