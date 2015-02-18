#!/bin/sh
SCRIPTS_PATH=~/shellscripts
TODO_SCRIPT=todo
TODO_DB_FILENAME=todo.db
echo '#!/bin/sh' > $SCRIPTS_PATH/$TODO_SCRIPT
echo 'exec scala "$0" '$SCRIPTS_PATH/$TODO_DB_FILENAME'' >> $SCRIPTS_PATH/$TODO_SCRIPT
echo '!#' >> $SCRIPTS_PATH/$TODO_SCRIPT
cat src/main/scala/TodoScript.scala >> $SCRIPTS_PATH/$TODO_SCRIPT
chmod 755 $SCRIPTS_PATH/$TODO_SCRIPT
