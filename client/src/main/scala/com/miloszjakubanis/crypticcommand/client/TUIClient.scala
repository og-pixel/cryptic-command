package com.miloszjakubanis.crypticcommand.client

import com.googlecode.lanterna.{TerminalSize, TextColor}
import com.googlecode.lanterna.gui2.{BasicWindow, Button, DefaultWindowManager, EmptySpace, GridLayout, Label, MultiWindowTextGUI, Panel, TextBox}
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory

import java.io.IOException

//TODO this will be the latarena library
class TUIClient {

  @throws[IOException]
  def run(): Unit = {
    val terminal = new DefaultTerminalFactory().createTerminal
    val screen = new TerminalScreen(terminal)
    screen.startScreen()
    // Create panel to hold components
    val panel = new Panel
    panel.setLayoutManager(new GridLayout(2))
    panel.addComponent(new Label("Forename"))
    panel.addComponent(new TextBox)
    panel.addComponent(new Label("Surname"))
    panel.addComponent(new TextBox)
    panel.addComponent(new EmptySpace(new TerminalSize(0, 0))) // Empty space underneath labels

    panel.addComponent(new Button("Submit"))
    // Create window to hold the panel
    val window = new BasicWindow
    window.setComponent(panel)
    // Create gui and start gui
    val gui = new MultiWindowTextGUI(screen, new DefaultWindowManager, new EmptySpace(TextColor.ANSI.BLUE))
    gui.addWindowAndWait(window)
  }

}
