#!/bin/sh
SCRIPTS_PATH=/Users/sjumilli/shellscripts
TODO_SCRIPT=todo.sh
echo '#!/bin/sh' > $SCRIPTS_PATH/$TODO_SCRIPT
echo 'exec scala "$0" "$@"' >> $SCRIPTS_PATH/$TODO_SCRIPT
echo '!#' >> $SCRIPTS_PATH/$TODO_SCRIPT
cat src/main/scala/TodoScript.scala >> $SCRIPTS_PATH/$TODO_SCRIPT

